package com.employetracker.Expection;

/**
 * @author : Vishal Srivastava
 * @Date : 27-08-2021
 **/
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
