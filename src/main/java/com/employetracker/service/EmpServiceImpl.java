package com.employetracker.service;

import com.employetracker.Expection.DefinedExpection;
import com.employetracker.modal.Employee;
import com.employetracker.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : Vishal Srivastava
 * @Date : 22-08-2020
 **/

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepository empRepo;

    @Override
    public List<Employee> allEmployees() {
        return empRepo.findAll();
    }

    @Override
    public Employee addEmp(Employee employee) {
        Employee emp = new Employee();
        emp.setSapId(employee.getSapId());
        emp.setEmpName(employee.getEmpName());
        emp.setAddress(employee.getAddress());
        emp.setCountry(employee.getCountry());
        emp.setNumber(employee.getNumber());

        return empRepo.save(emp);
    }

    @Override
    public Employee getBySapId(int id) {
        return empRepo.findBySapId(id);
    }

    @Override
    public Employee updateEmp(Employee employee) {
        Employee findEmp = empRepo.findBySapId(employee.getSapId());

        if (findEmp != null) {
            findEmp.setEmpName(employee.getEmpName().isEmpty() ? findEmp.getEmpName() : employee.getEmpName());
            findEmp.setAddress(employee.getAddress().isEmpty() ? findEmp.getAddress() : employee.getAddress());
            findEmp.setCountry(employee.getCountry().isEmpty() ? findEmp.getCountry() : employee.getCountry());
            findEmp.setNumber(employee.getNumber() == 0 ? findEmp.getNumber() : employee.getNumber());
            empRepo.save(findEmp);
        }
        return findEmp;
    }

    @Override
    public String deleteEmp(int sapId) {
        Employee emp = empRepo.findBySapId(sapId);

        if (emp != null) {
            empRepo.delete(emp);
            return "Success";
        }
        return "Fails";
    }
}
