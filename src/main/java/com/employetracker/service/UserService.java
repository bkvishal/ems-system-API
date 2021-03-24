package com.employetracker.service;


import com.employetracker.modal.UpdatePassword;
import com.employetracker.modal.User;
import com.employetracker.modal.UserB;

/**
 * @author : Vishal Srivastava
 * @Date : 04-02-2021
 **/

public interface UserService {

    UserB signUp(User user);
    UserB loginWithEmail(String email);
    UserB loginWithMobNumber(Double mobNumber);
    void updatePassword(UpdatePassword pass);
}
