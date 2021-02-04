package com.employetracker.service;

import com.employetracker.commonEntity.Response;
import com.employetracker.modal.UpdatePassword;
import com.employetracker.modal.User;

/**
 * @author : Vishal Srivastava
 * @Date : 04-02-2021
 **/

public interface UserService {

    Response signUp(User user);
    Response loginWithEmail(String email);
    Response loginWithMobNumber(Double mobNumber);
    Response updatePassword(UpdatePassword pass);
}
