package com.employetracker.modal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author : Vishal Srivastava
 * @Date : 04-02-2021
 **/

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String userName;
    private String address;
    private String email;
    private String password;
    private Double mobNumber;
    private String resetToken;


    public User(String userName) {
        this.userName = userName;
    }
}
