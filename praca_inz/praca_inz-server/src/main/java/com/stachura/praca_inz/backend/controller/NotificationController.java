package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.web.dto.NotificationListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.NotificationConverter;
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
@RequestMapping("/secured/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public @ResponseBody
//    List<NotificationListElementDto> getAllNotificationsForUser() {
//        return notificationService.get();
//    }


    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<NotificationListElementDto> getUnreadedAllDevicesForLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return notificationService.getUnreadedAllNotificationsForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/user/readed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<NotificationListElementDto> getReadedAllDevicesForLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return notificationService.getReadedAllNotificationsForLoggedUser(auth.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Notification get(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

//    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public @ResponseBody
//    Notification getOfficeById(@RequestParam String name) {
//        return notificationService.g(name);
//    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody Notification notification) {
        notificationService.createNewNotification(notification);
        HttpHeaders headers = new HttpHeaders();
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(NotificationController.class).get(notification.getId()));
        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody NotificationListElementDto notificationListElementDto) {
        Notification notification= notificationService.getNotificationById(notificationListElementDto.getId());
        notificationService.updateNotification(NotificationConverter.toNotification(notificationListElementDto,notification));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        notificationService.deleteNotificationById(id);
    }
}
