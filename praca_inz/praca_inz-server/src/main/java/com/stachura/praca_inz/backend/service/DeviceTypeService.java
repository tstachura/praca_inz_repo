package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.web.dto.DeviceTypeListElementDto;


import java.util.List;

public interface DeviceTypeService {

    List<DeviceTypeListElementDto> getAllDeviceTypes();

    void createNewDeviceType(String type) throws ServiceException;

    void deleteDeviceTypeById(Long id) throws ServiceException;
}