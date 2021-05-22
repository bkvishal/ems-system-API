package com.employetracker.service;

import com.employetracker.Expection.DefinedExpection;
import com.employetracker.modal.Employee;
import com.employetracker.repository.EmpRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author : Vishal Srivastava
 * @Date : 22-08-2020
 **/

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    //constructor based field injection
    private final EmpRepository empRepo;

    public EmpServiceImpl(EmpRepository empRepo) {
        this.empRepo = empRepo;
    }

    @Override
    public List<Employee> allEmployees() {
        log.info("Trying to get all emp list");
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

        log.info("adding emp is done right here in addEmp service method");

        return empRepo.save(emp);


    }

    @Override
    public Employee getBySapId(int id) {
         Employee emp = empRepo.findBySapId(id);
         if (emp!=null) {
             log.info("Getting the emp with id " + id);
             return emp;
         }
         log.error("Unable to get emp with id" +  id);

         return emp;
    }

    @Override
    public Employee updateEmp(Employee employee) {
        Employee findEmp = empRepo.findBySapId(employee.getSapId());

        if (findEmp != null) {
            findEmp.setEmpName(employee.getEmpName().isEmpty() ? findEmp.getEmpName() : employee.getEmpName());
            findEmp.setAddress(employee.getAddress().isEmpty() ? findEmp.getAddress() : employee.getAddress());
            findEmp.setCountry(employee.getCountry().isEmpty() ? findEmp.getCountry() : employee.getCountry());
            findEmp.setNumber(employee.getNumber() == 0 ? findEmp.getNumber() : employee.getNumber());
            log.info("Updated correctly");
            empRepo.save(findEmp);
        }
        return findEmp;
    }

    @Override
    public String deleteEmp(int sapId) {
        Employee emp = empRepo.findBySapId(sapId);

        if (emp != null) {
            empRepo.delete(emp);
            log.info("Succesfully deleted" +  sapId);
            return "Success";
        }
        log.error("Unable to delete emp with id" + sapId);
        return "Fails";
    }
}
