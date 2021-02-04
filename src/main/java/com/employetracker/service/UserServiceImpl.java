package com.employetracker.service;

import com.employetracker.commonEntity.Response;
import com.employetracker.modal.UpdatePassword;
import com.employetracker.modal.User;
import org.springframework.stereotype.Service;

/**
 * @author : Vishal Srivastava
 * @Date : 04-02-2021
 **/

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Response signUp(User user) {
        return null;
    }

    @Override
    public Response loginWithEmail(String email) {
        return null;
    }

    @Override
    public Response loginWithMobNumber(Double mobNumber) {
        return null;
    }

    @Override
    public Response updatePassword(UpdatePassword pass) {
        return null;
    }
}
