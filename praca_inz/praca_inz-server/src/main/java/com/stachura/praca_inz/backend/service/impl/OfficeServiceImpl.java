package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.DepartmentRepository;
import com.stachura.praca_inz.backend.repository.OfficeRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.OfficeService;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('OFFICE_READ')")
    public Office getOfficeById(Long id) throws AppBaseException {
        Office office = officeRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (office.isDeleted()) {
            return null;
        }
        return office;
    }

    @Override
    public List<CompanyStructuresListElementDto> getAllOfficesForCompany(Long id) {
        List<Office> offices = Lists.newArrayList(officeRepository.findAll()).stream().filter(x -> x.getDepartment().getCompany().getId().equals(id)).collect(Collectors.toList());
        List<CompanyStructuresListElementDto> officesDto = new ArrayList<>();
        for (Office a : offices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDepartment());
                Hibernate.initialize(a.getDepartment().getCompany());
                officesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return officesDto;
    }

    @Override
    public List<CompanyStructuresListElementDto> getAllOfficesForDepartment(Long id) {
        List<Office> offices = Lists.newArrayList(officeRepository.findAll()).stream().filter(x -> x.getDepartment().getId().equals(id)).collect(Collectors.toList());
        List<CompanyStructuresListElementDto> officesDto = new ArrayList<>();
        for (Office a : offices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDepartment());
                Hibernate.initialize(a.getDepartment().getCompany());
                officesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return officesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('OFFICE_LIST_READ')")
    public List<CompanyStructuresListElementDto> getAll(String username) throws AppBaseException {
        List<Office> offices;
        User user=userRepository.findByUsername(username).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if(user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
            offices = Lists.newArrayList(officeRepository.findAll());
        } else{
            offices = Lists.newArrayList(officeRepository.findAll()).stream().filter(x->x.getDepartment().getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<CompanyStructuresListElementDto> officesDto = new ArrayList<>();
        for (Office a : offices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDepartment());
                Hibernate.initialize(a.getDepartment().getCompany());
                officesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return officesDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('OFFICE_CREATE')")
    public void create(CompanyStructureAddDto companyStructureAddDto)throws AppBaseException {
        Department department=departmentRepository.findById(companyStructureAddDto.getDepartmentId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        officeRepository.save(CompanyStructureConverter.toOffice(companyStructureAddDto,department));

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('OFFICE_UPDATE')")
    public void update(CompanyStructureEditDto companyStructureEditDto) throws AppBaseException {
        Office beforeOffice=officeRepository.findById(companyStructureEditDto.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Department department=departmentRepository.findById(companyStructureEditDto.getParentId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        officeRepository.save(CompanyStructureConverter.toOffice(companyStructureEditDto,beforeOffice,department));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('OFFICE_DELETE')")
    public void delete(Long id) throws AppBaseException {
        officeRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }
}
