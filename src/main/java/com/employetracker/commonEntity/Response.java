package com.employetracker.commonEntity;

import lombok.Data;

import java.util.List;

/**
 * @author : Vishal Srivastava
 * @Date : 22-08-2020
 **/

@Data
public class Response {

    private Boolean status;
    private String error;
    private String httpCode;
    private List<?> result;
}
