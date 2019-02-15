package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.EntityOptimisticLockException;
import com.stachura.praca_inz.backend.exception.ServiceException;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.model.security.UserRole;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.repository.WarehouseRepository;
import com.stachura.praca_inz.backend.service.EmailService;
import com.stachura.praca_inz.backend.service.UserService;
import com.stachura.praca_inz.backend.web.dto.PasswordResetDto;
import com.stachura.praca_inz.backend.web.dto.converter.UserConverter;
import com.stachura.praca_inz.backend.web.dto.user.*;
import com.stachura.praca_inz.backend.web.utils.PasswordGenerator;
import org.hibernate.Hibernate;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Qualifier("userPasswordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Override
    public List<UserListElementDto> getAllUsers() {
        List<User> users = Lists.newArrayList(userRepository.findAll());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }
        }
        return usersDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PROFILE_VIEW_READ')")
    public ProfileInfoDto getProfile(String name) throws EntityNotInDatabaseException {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        if (user.isEnabled()) {
            return UserConverter.toProfileInfo(user);
        }
        return null;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PROFILE_EDIT_READ')")
    public ProfileEditDto getProfileEdit(String username) throws EntityNotInDatabaseException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());

        if (user.isEnabled()) {
            return UserConverter.toProfileEditDto(user, warehouseRepository.findAll().stream().filter(x -> x.getWarehouseType().equals(WarehouseType.USER)&&(x.getUsers().contains(user)||x.getUser().equals(user))).findFirst().orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        }
        return null;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('LOGGED_USER_READ')")
    public LoggedUserDto getLoggedUser(String name) throws EntityNotInDatabaseException {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        LoggedUserDto loggedUserDto = UserConverter.toLoggedUser(user);
        return loggedUserDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_VIEW_READ')")
    public UserInfoDto getUserInfo(String name) throws EntityNotInDatabaseException {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        if (user.isEnabled()) {
            return UserConverter.toUserInfo(user);
        }
        return null;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_EDIT_READ')")
    public UserEditDto getUserEdit(Long id) throws EntityNotInDatabaseException {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(user.getUsername());
        Hibernate.initialize(user.getUserRoles());
        Hibernate.initialize(user.getAuthorities());
        Hibernate.initialize(user.getNotifications());
        if (user.isEnabled()) {
            return UserConverter.toUserEditDto(user, warehouseRepository.findAll().stream().filter(x -> x.getWarehouseType().equals(WarehouseType.USER)).findFirst().orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        }
        return null;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_FOR_MANAGER_READ')")
    public List<UserListElementDto> getAllUsersForManager(String username) throws EntityNotInDatabaseException {
        Long officeId = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).getOffice().getId();
        List<User> users = Lists.newArrayList(userRepository.findAll()).stream().filter(x -> x.getOffice().getId().equals(officeId)).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }
        }
        return usersDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_FOR_COMPANY_ADMIN_READ')")
    public List<UserListElementDto> getAllUsersForCompanyAdmin(String username) throws EntityNotInDatabaseException {
        Long companyId = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).getOffice().getDepartment().getCompany().getId();
        List<User> users = Lists.newArrayList(userRepository.findAll()).stream().filter(x -> x.getOffice().getDepartment().getCompany().getId().equals(companyId)).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }
        }
        return usersDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ALL_WAREHOUSEMAN_READ')")
    public List<UserListElementDto> getAllWarehousemen(Long id) {
        List<User> users = Lists.newArrayList(userRepository.findAll()).stream().filter(x -> x.getOffice().getId().equals(id) && x.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()).contains("WAREHOUSEMAN")).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }

        }
        return usersDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('LOGGED_USER_ROLES_READ')")
    public UserRolesDto getLoggedUserRoles(String name) throws EntityNotInDatabaseException {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return UserConverter.toUserRoles(user);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_ROLES_READ')")
    public UserRolesDto getUserRoles(Long id) throws EntityNotInDatabaseException {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return UserConverter.toUserRoles(user);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public void deleteUser(Long id) throws EntityNotInDatabaseException {
        userRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setEnabled(false);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PASSWORD_ADMIN_READ')")
    public PasswordInfoForAdmin getPasswordForAdmin(Long id) throws EntityNotInDatabaseException {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return PasswordInfoForAdmin.builder().userVersion(user.getVersion()).id(id).build();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PASSWORD_ADMIN_UPDATE')")
    public void updatePasswordForAdmin(PasswordInfoForAdmin passwordInfoForAdmin) throws EntityNotInDatabaseException, EntityOptimisticLockException {
       try {
           User user = userRepository.findById(passwordInfoForAdmin.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
           userRepository.detach(user);
           user.setPassword(passwordEncoder.encode(passwordInfoForAdmin.getNewPassword()));
           user.setVersion(passwordInfoForAdmin.getUserVersion());
           userRepository.saveAndFlush(user);
       }catch (StaleObjectStateException e){
           throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
       }
    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PASSWORD_READ')")
    public PasswordInfoDto getPassword(String username) throws EntityNotInDatabaseException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return PasswordInfoDto.builder().userVersion(user.getVersion()).build();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PASSWORD_UPDATE')")
    public void updatePassword(PasswordInfoDto passwordInfoDto, String username) throws EntityNotInDatabaseException, ServiceException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));

        if (!(passwordInfoDto.getNewPassword().length() >= 8 && passwordInfoDto.getOldPassword().length() >= 8)) {
            throw new ServiceException(ServiceException.INCORRECT_LENGTH_PASSWORD);
        }
        if (!passwordEncoder.matches(passwordInfoDto.getOldPassword(), user.getPassword())) {
            throw new ServiceException(ServiceException.INCORRECT_PASSWORD);
        }
        if (!passwordEncoder.matches(passwordInfoDto.getNewPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordInfoDto.getNewPassword()));
            user.setVersion(passwordInfoDto.getUserVersion());
            userRepository.saveAndFlush(user);

        } else {
            throw new ServiceException(ServiceException.SAME_PASSWORD);
        }

    }

    @Override
    public void resetPassword(PasswordResetDto passwordResetDto) throws EntityNotInDatabaseException, ServiceException {
        User user = userRepository.findByUsername(passwordResetDto.getUsername()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_USERNAME));
        if (user.getUserdata().getEmail().equals(passwordResetDto.getEmail())) {
            PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                    .useDigits(true)
                    .useLower(true)
                    .useUpper(true)
                    .build();
            String password = passwordGenerator.generate(8);
            if (user.getUserdata().getLanguage().equals("ENG")) {
                emailService.sendSimpleMessage(user.getUserdata().getEmail(), "Password reset", "Password: " + password);
            } else {
                emailService.sendSimpleMessage(user.getUserdata().getEmail(), "Reset hasła", "Hasło: " + password);
            }
            user.setPassword(passwordEncoder.encode(password));
            userRepository.saveAndFlush(user);
        } else{
            throw  new ServiceException(ServiceException.INCORRECT_PASSWORD_RESET_DATA);
        }

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_FOR_REPORT_READ')")
    public List<UserListElementDto> getAllUsersForReport(String name) {
        List<User> users = Lists.newArrayList(userRepository.findAll()).stream().filter(x->!x.getUsername().equals(name)).collect(Collectors.toList());
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }
        }
        return usersDto;
    }

    @Override
    public List<UserListElementDto> getAllUsersForWarehouseEdit(Long id) throws EntityNotInDatabaseException {
        Warehouse warehouse=warehouseRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        List<User> users = Lists.newArrayList(userRepository.findAll().stream().filter(x->x.getOffice().getId().equals(warehouse.getOffice().getId())).collect(Collectors.toList()));
        List<UserListElementDto> usersDto = new ArrayList<>();
        for (User a : users) {
            if (a.isEnabled()) {
                usersDto.add(UserConverter.toUserListElement(a));
            }
        }
        return usersDto;
    }


}
