package com.employetracker.service;

import com.employetracker.Expection.UserNotFoundExpection;
import com.employetracker.commonEntity.Response;
import com.employetracker.modal.UpdatePassword;
import com.employetracker.modal.User;
import com.employetracker.modal.UserB;
import com.employetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Vishal Srivastava
 * @Date : 04-02-2021
 **/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public UserB signUp(User user) {
        UserB userWrap = new UserB();
        User userSaved = userRepo.save(user);

        // returing a user Bean object to show in a response
        userWrap.setUserId(userSaved.getUserId());
        userWrap.setUserName(userSaved.getUserName());
        userWrap.setAddress(userSaved.getAddress());
        userWrap.setEmail(userSaved.getEmail());
        userWrap.setMobNumber(userSaved.getMobNumber());

        return userWrap;

    }

    @Override
    public UserB loginWithEmail(String email) {
        UserB userWrap = new UserB();
        User userFetch = userRepo.findByEmail(email);

        if (userFetch == null) {
            throw new UserNotFoundExpection("Unable to find user with email");
        }

        userWrap.setUserId(userFetch.getUserId());
        userWrap.setUserName(userFetch.getUserName());
        userWrap.setAddress(userFetch.getAddress());
        userWrap.setEmail(userFetch.getEmail());
        userWrap.setMobNumber(userFetch.getMobNumber());

        return userWrap;
    }

    @Override
    public UserB loginWithMobNumber(Double mobNumber) {
        UserB userWrap = new UserB();
        User userFetch = userRepo.findByMobNumber(mobNumber);

        if (userFetch == null) {
            throw new UserNotFoundExpection("Unable to find user with number");
        }

        userWrap.setUserId(userFetch.getUserId());
        userWrap.setUserName(userFetch.getUserName());
        userWrap.setAddress(userFetch.getAddress());
        userWrap.setEmail(userFetch.getEmail());
        userWrap.setMobNumber(userFetch.getMobNumber());

        return userWrap;
    }

    @Override
    public void updatePassword(UpdatePassword pass) {

    }
}
