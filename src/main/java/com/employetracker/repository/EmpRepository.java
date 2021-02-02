package com.employetracker.repository;

import com.employetracker.modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Vishal Srivastava
 * @Date : 22-08-2020
 **/

@Repository
public interface EmpRepository extends JpaRepository<Employee, Integer> {

    Employee findBySapId(int id);
}
