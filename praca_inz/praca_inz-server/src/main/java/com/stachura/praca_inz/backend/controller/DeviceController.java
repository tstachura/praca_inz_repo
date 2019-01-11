package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.service.DeviceService;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/secured/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAll() throws ServiceException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceService.getAllDevices(auth.getName());
    }

    @RequestMapping(value = "/company/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForCompany(@PathVariable Long id) {
        return deviceService.getAllDevicesForCompany(id);
    }

    @RequestMapping(value = "/department/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForDepartment(@PathVariable Long id) {
        return deviceService.getAllDevicesForDepartment(id);
    }

    @RequestMapping(value = "/office/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForOffice(@PathVariable Long id) {
        return deviceService.getAllDevicesForOffice(id);
    }

    @RequestMapping(value = "/warehouse/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForWarehouse(@PathVariable Long id) {
        return deviceService.getAllDevicesForWarehouse(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceService.getAllDevicesForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/warehouseman", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForLoggedWarehouseman() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceService.getAllDevicesForLoggedWarehouseman(auth.getName());
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForShipmentRequest() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return deviceService.getAllDevicesForShipmentRequest(auth.getName());
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<DeviceListElementDto> getAllDevicesForLoggedUser(@PathVariable String username) {
        return deviceService.getAllDevicesForLoggedUser(username);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Device get(@PathVariable Long id) throws ServiceException {
        return deviceService.getDeviceById(id);
    }

    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Device getParameters(@PathVariable Long id) throws ServiceException {
        return deviceService.getDeviceById(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody Device device) throws ServiceException {
        try {
            deviceService.createNewDevice(device);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Device device) {
        try {
            deviceService.updateDevice(device);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws ServiceException {
        deviceService.deleteDeviceById(id);
    }
}
