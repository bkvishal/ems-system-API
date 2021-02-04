package com.employetracker.modal;

import lombok.Data;

/**
 * @author : Vishal Srivastava
 * @Date : 04-02-2021
 **/

@Data
public class UpdatePassword {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

}
