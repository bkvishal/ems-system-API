package com.employetracker.service;

import com.employetracker.modal.Employee;

import java.util.List;
import java.util.Optional;

/**
 * @author : Vishal Srivastava
 * @Date : 22-08-2020
 **/


public interface EmpService {

   List<Employee> allEmployees();
   Employee addEmp(Employee employee);
   Employee getBySapId(int id);
   Employee updateEmp(Employee employee);
   String deleteEmp(int sapId);

   List<Employee> getByDesignation(String designation);

}
