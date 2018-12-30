package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.repository.interfaces.DeliveryRepository;
import com.stachura.praca_inz.backend.service.DeliveryService;
import com.stachura.praca_inz.backend.web.dto.DeliveryListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeliveryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_READ')")
    public Delivery getDeliveryById(Long id) {
        Delivery delivery=deliveryRepository.find(id);
        if(delivery.isDeleted()){
            return null;
        }
        return delivery ;
    }

//    @Override
//    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('DELIVERY_READ')")
//    public Delivery getDeliveryByName(String name) {
//        return deliveryRepository.find(name);
//    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DELIVERY_LIST_READ')")
    public List<DeliveryListElementDto> getAllDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        List<DeliveryListElementDto> companiesDto = new ArrayList<>();
        for (Delivery a : deliveries) {
            if(!a.isDeleted()) {
                companiesDto.add(DeliveryConverter.toDeliveryListElement(a));
            }
        }
        return companiesDto;
    }

    //TODO
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_CREATE')")
    public void createNewDelivery(Delivery delivery) {
        try {
            deliveryRepository.create(delivery);

        } catch (EntityException e) {

        }

    }

    //TODO
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_UPDATE')")
    public Delivery updateDelivery(Delivery delivery) {
        Delivery tmp = new Delivery();
        try {
            if (!deliveryRepository.find(delivery.getId()).isDeleted()) {
                tmp = deliveryRepository.update(delivery);
            }
        } catch (EntityException e) {

        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_DELETE')")
    public void deleteDeliveryById(Long id) {
        deliveryRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DELIVERY_DELETE')")
    public void deleteDelivery(Delivery delivery) {
        deliveryRepository.find(delivery.getId()).setDeleted(true);
    }
}
