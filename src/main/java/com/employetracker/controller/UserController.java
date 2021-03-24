package com.employetracker.controller;

import com.employetracker.commonEntity.Response;
import com.employetracker.modal.User;
import com.employetracker.modal.UserB;
import com.employetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author : Vishal Srivastava
 * @Date : 04-02-2021
 **/

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/signUp")
    public ResponseEntity<Response> createUser(@RequestBody User user) {
        Response rs = new Response();
        UserB result = userService.signUp(user);
        if (result!= null) {
         rs.setResult(Collections.singletonList(result));
         rs.setStatus(true);
         rs.setHttpCode("201");
         return new ResponseEntity<>(rs, HttpStatus.CREATED);
        }
        rs.setStatus(false);
        rs.setError("Unable to create");
        return new ResponseEntity<>(rs, HttpStatus.EXPECTATION_FAILED);
    }


    @GetMapping(value = "/loginWithEmail")
    public ResponseEntity<Response> loginWithEmail(String email) {
        Response rs = new Response();
        UserB result = userService.loginWithEmail(email);

        if (result!= null) {
            rs.setResult(Collections.singletonList(result));
            rs.setStatus(true);
            rs.setStatus(true);
            rs.setHttpCode("200");
            return new ResponseEntity<>(rs, HttpStatus.FOUND);
        }
        rs.setStatus(false);
        rs.setError("Unable to find");
        return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
    }
}
