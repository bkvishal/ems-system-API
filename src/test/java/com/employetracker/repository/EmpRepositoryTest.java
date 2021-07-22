package com.employetracker.repository;

import com.employetracker.modal.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * @author : Vishal Srivastava
 * @Date : 21-07-2021
 **/

@DataJpaTest
class EmpRepositoryTest {
    @Autowired
    EmpRepository empRepository;

    @Test
    void itShouldFindEmployeeBySapId() {

        Employee employee = new Employee();
        employee.setEmpName("vishal");
        employee.setSapId(24);
        employee.setImageUri("wow");
        employee.setDesignation("top");
        employee.setNumber(989686465);
        employee.setCountry("india");

        empRepository.save(employee);

        //given
        int id = 24;

        boolean result;

        //when

        if(empRepository.findBySapId(id)!=null) {
            result = true;
        } else {
            result = false;
        }

        //then
        assertThat(result).isTrue();
    }

    @Test
    void findByDesignation() {
    }
}