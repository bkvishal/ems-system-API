package com.employetracker.modal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author : Vishal Srivastava
 * @Date : 22-08-2020
 **/

@Entity
@Data
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sapId;
    private String empName;
    private String Address;
    private long number;
    private String country;
}
