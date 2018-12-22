package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceRepository;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.service.DeviceService;
import com.stachura.praca_inz.backend.web.dto.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_READ')")
    public Device getDeviceById(Long id) {
        return deviceRepository.find(id);
    }

    /*@Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_READ') and hasAuthority('DEPARTMENT_READ')")
    public Device getDeviceByName(String name) {
        return deviceRepository.find(name);
    }*/


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
        }
        return devicesDto;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEVICE_LIST_READ')")
    public List<DeviceListElementDto> getAllDevicesForLoggedUser(String username) {
        List<Device> devices = deviceRepository.findAll().stream().filter(x -> x.getWarehouse().getUser().getUsername().equals(username) &&
                x.getWarehouse().getOffice() == null).collect(Collectors.toList());
        List<DeviceListElementDto> devicesDto = new ArrayList<>();
        for (Device a : devices) {
            devicesDto.add(DeviceConverter.toDeviceListElementDto(a));
        }
        return devicesDto;
    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_CREATE')")
    public void createNewDevice(Device device) {
        try {
            deviceRepository.create(device);
        } catch (EntityException e) {

        }
    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_UPDATE')")
    public Device updateDevice(Device device) {
        Device tmp = new Device();
        try {
            tmp = deviceRepository.update(device);
        } catch (EntityException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_DELETE')")
    public void deleteDeviceById(Long id) {
        deviceRepository.remove(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEVICE_DELETE')")
    public void deleteDevice(Device device) {
        deviceRepository.remove(device);
    }
}
