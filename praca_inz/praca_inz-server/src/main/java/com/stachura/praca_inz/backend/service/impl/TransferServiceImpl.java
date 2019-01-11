package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.TransferRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.TransferService;
import com.stachura.praca_inz.backend.web.dto.TransferListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.TransferConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService {


    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('TRANSFER_READ')")
    public Transfer getTransferById(Long id) throws ServiceException {
        Transfer transfer = transferRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (transfer.isDeleted()) {
            return null;
        }
        return transfer;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('TRANSFER_LIST_READ')")
    public List<TransferListElementDto> getAllTransfersForLoggedUser(String username) {
        List<Transfer> transfers = Lists.newArrayList(transferRepository.findAll()).stream().filter(x -> x.getSenderWarehouse().getUser().getUsername().equals(username) &&
                x.getRecieverWarehouse().getUser().getUsername().equals(username) || x.getUsername().equals(username)).collect(Collectors.toList());
        List<TransferListElementDto> transferListElementDtos = new ArrayList<>();
        for (Transfer a : transfers) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getRecieverWarehouse());
                Hibernate.initialize(a.getSenderWarehouse());
                transferListElementDtos.add(TransferConverter.toTransferListElementDto(a));
            }
        }
        return transferListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('TRANSFER_READ')")
    public List<TransferListElementDto> getAllTransfers(String username) throws ServiceException {
        List<Transfer> transfers;
        User user=userRepository.findByUsername(username).orElseThrow(()->new ServiceException());
        if(user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
            transfers = Lists.newArrayList(transferRepository.findAll());
        } else{
            transfers = Lists.newArrayList(transferRepository.findAll()).stream().filter(x->x.getRecieverWarehouse().getOffice().getDepartment().getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<TransferListElementDto> transferListElementDtos = new ArrayList<>();
        for (Transfer a : transfers) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getRecieverWarehouse());
                Hibernate.initialize(a.getSenderWarehouse());
                transferListElementDtos.add(TransferConverter.toTransferListElementDto(a));
            }
        }
        return transferListElementDtos;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_CREATE')")
    public void createNewTransfer(Transfer transfer)throws ServiceException {
            transferRepository.save(transfer);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_UPDATE')")
    public void updateTransfer(Transfer transfer) throws ServiceException {
        transferRepository.save(transfer);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_DELETE')")
    public void deleteTransferById(Long id) throws ServiceException {
        transferRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TRANSFER_DELETE')")
    public void deleteTransfer(Transfer transfer) throws ServiceException {
        transferRepository.findById(transfer.getId()).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }
}
