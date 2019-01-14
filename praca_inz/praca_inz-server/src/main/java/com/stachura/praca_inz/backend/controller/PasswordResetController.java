package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reset")
@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = AppBaseException.class)
public class PasswordResetController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@PathVariable String username) throws AppBaseException {
        userService.resetPassword(username);
    }
}
