package com.employetracker.service;

import com.employetracker.modal.Employee;
import com.employetracker.repository.EmpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author : Vishal Srivastava
 * @Date : 21-07-2021
 **/

@SpringBootTest
class EmpServiceImplTest {

    @Mock
    EmpRepository empRepository;

    @InjectMocks
    EmpServiceImpl empService;

    List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        employeeList = Arrays.asList(
                new Employee(1, "vishal", "CEO", "god home", 99765465, "india","asdas"),
                new Employee(2, "Entium", "VC", "god home", 986546656, "india","asdasdas")
        );
    }

    @Test
    void allEmployees() {
        // mocking the repo
        when(empRepository.findAll()).thenReturn(employeeList); // mocking

        int result = empService.allEmployees().size();

        // here we are using assertJ instead of assert from junit
        assertThat(result).isEqualTo(2);

    }

    @Test
    @DisplayName("Getting employee by id")
    void getBySapId() {
        int id = 1;

        Employee employee = employeeList.stream().filter(s -> s.getSapId() == 1).findAny().orElse(null);
        when(empRepository.findBySapId(id)).thenReturn(employee);
        // option 1
       /* boolean result = false;
        if (empService.getBySapId(id)!=null)
        {
            result = true;
        }
        assertThat(result).isTrue();*/

       // option 2
        int result = empService.getBySapId(id).getSapId();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void addEmp() {
        Employee emp =new Employee(3, "max", "LEAD", "west", 65464, "india","asdas");

        when(empRepository.save(emp)).thenReturn(emp);

        Employee emp1 = empService.addEmp(emp);
        assertThat(emp1).isEqualTo(emp);
    }

    @Test
    void deleteEmp() {
        int id = 2;
        Employee employee = employeeList.stream().filter(s->s.getSapId()==2).findAny().orElse(null);

        when(empRepository.findBySapId(id)).thenReturn(employee);
        empService.deleteEmp(id);
        // verify internally call assert so no need to add assert statement.
        verify(empRepository).delete(employee);
    }

    @Test
    void updateEmp() {
        Employee emp =new Employee(3, "max", "DGM", "west", 65464, "india","asdas");

        // mocking required repo method
        when(empRepository.findBySapId(emp.getSapId())).thenReturn(emp);
        when(empRepository.save(emp)).thenReturn(emp);

        Employee emp1 = empService.updateEmp(emp);
        assertThat(emp1).isEqualTo(emp);
    }
}