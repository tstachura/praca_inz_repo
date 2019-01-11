package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.DeliveryRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.DeliveryService;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeliveryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_READ')")
    public Delivery getDeliveryById(Long id) throws ServiceException {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (delivery.isDeleted()) {
            return null;
        }
        return delivery;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_LIST_READ')")
    public List<DeliveryListElementDto> getAllDeliveries(String username) throws ServiceException {
        List<Delivery> deliveries;
        User user=userRepository.findByUsername(username).orElseThrow(() -> new ServiceException());
        if (user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
            deliveries = Lists.newArrayList(deliveryRepository.findAll());
        } else {
            deliveries = Lists.newArrayList(deliveryRepository.findAll()).stream().filter(x->x.getSenderWarehouse().getOffice().getDepartment().getCompany()
                    .getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<DeliveryListElementDto> companiesDto = new ArrayList<>();
        for (Delivery a : deliveries) {
            if (!a.isDeleted()) {
                companiesDto.add(DeliveryConverter.toDeliveryListElement(a));
            }
        }
        return companiesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_LIST_READ')")
    public List<DeliveryListElementDto> getAllDeliveriesForWarehouseman(String username) throws ServiceException {
        Long id=userRepository.findByUsername(username).orElseThrow(() -> new ServiceException()).getId();
        List<Delivery> deliveries = Lists.newArrayList(deliveryRepository.findAll()).stream().filter(x -> x.getSenderWarehouse().getUser().getId().equals(id)).collect(Collectors.toList());
        List<DeliveryListElementDto> companiesDto = new ArrayList<>();
        for (Delivery a : deliveries) {
            if (!a.isDeleted()) {
                companiesDto.add(DeliveryConverter.toDeliveryListElement(a));
            }
        }
        return companiesDto;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_CREATE')")
    public void createNewDelivery(Delivery delivery) throws ServiceException {
            deliveryRepository.save(delivery);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_UPDATE')")
    public void updateDelivery(Delivery delivery) throws ServiceException {
        deliveryRepository.save(delivery);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_DELETE')")
    public void deleteDeliveryById(Long id) throws ServiceException {
        deliveryRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }



}
