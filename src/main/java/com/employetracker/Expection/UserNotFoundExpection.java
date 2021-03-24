package com.employetracker.Expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Vishal Srivastava
 * @Date : 08-03-2021
 **/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundExpection extends RuntimeException {
    public UserNotFoundExpection(String s) {
        super(s);
    }
}
