package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.model.ExternalTransfer;
import com.stachura.praca_inz.backend.service.ExternalTransferService;
import com.stachura.praca_inz.backend.web.dto.ExternalTransferListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured/external/transfer")
@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = SystemBaseException.class)
public class ExternalTransferController {

    @Autowired
    private ExternalTransferService externalTransferService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ExternalTransferListElementDto> getAll() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return externalTransferService.getAllDeliveries(auth.getName());
    }

    @RequestMapping(value = "/warehouseman",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ExternalTransferListElementDto> getAllForWarehouse() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return externalTransferService.getAllDeliveriesForWarehouseman(auth.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ExternalTransfer get(@PathVariable Long id) throws SystemBaseException {
        return externalTransferService.getDeliveryById(id);
    }

    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    void confirm(@PathVariable Long id) throws SystemBaseException {
       externalTransferService.confirmExternalTransfer(id);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody ExternalTransfer externalTransfer) throws SystemBaseException {
            externalTransferService.updateDelivery(externalTransfer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws SystemBaseException {
        externalTransferService.deleteDeliveryById(id);
    }
}
