package com.employetracker.controller;

import com.employetracker.commonEntity.Response;
import com.employetracker.modal.Employee;
import com.employetracker.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author : Vishal Srivastava
 * @Date : 22-08-2020
 **/

@RestController
@RequestMapping(name = "/empTracker")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<Response> getEmployee(){
        List<Employee> empList = empService.allEmployees();
        Response rs = new Response();

        if (empList.isEmpty()) {
            rs.setStatus(false);
            rs.setError("Something went wrong! Please try after some time.");
            rs.setHttpCode("404");
            rs.setResult(Collections.emptyList());
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        } else {
            rs.setStatus(true);
            rs.setHttpCode("200");
            rs.setResult(empList);
            return new ResponseEntity<>(rs, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/addEmp")
    public ResponseEntity<Response> addEmployee(@RequestBody Employee employee) {
        Employee emp = empService.addEmp(employee);
        Response rs = new Response();
        if (emp != null) {
            rs.setStatus(true);
            rs.setHttpCode("201");
            rs.setResult(Collections.singletonList(emp));
            return new ResponseEntity<>(rs, HttpStatus.CREATED);
        } else {
            rs.setStatus(false);
            rs.setError("Something went wrong! Unable to add employee");
            rs.setHttpCode("400");
            return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getByid/{SAP}")
    public  ResponseEntity<Response> findEmpBySapId(@PathVariable("SAP") int sapId) {
        Employee emp = empService.getBySapId(sapId);
        Response rs = new Response();
        if (emp != null) {
            rs.setStatus(true);
            rs.setHttpCode("200");
            rs.setResult(Collections.singletonList(emp));
            return new ResponseEntity<>(rs, HttpStatus.OK);
        } else {
            rs.setStatus(false);
            rs.setError("Something went wrong! Not able to find employee");
            rs.setHttpCode("404");
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/updateEmp")
    public ResponseEntity<Response> updateEmployee(@RequestBody Employee employee) {
        Employee emp = empService.updateEmp(employee);
        Response rs = new Response();
        if (emp != null) {
            rs.setStatus(true);
            rs.setHttpCode("200");
            rs.setResult(Collections.singletonList(emp));
            return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
        } else {
            rs.setStatus(false);
            rs.setError("Something went wrong! Employee Detail is not updated");
            rs.setHttpCode("400");
            return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/deleteEmp/{SAP}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable("SAP") int sapId) {
        String result = empService.deleteEmp(sapId);
        Response rs = new Response();
        if(result.equals("Success")) {
            rs.setStatus(true);
            rs.setHttpCode("200");
            rs.setResult(Collections.singletonList("Successfully deleted!"));
            return new ResponseEntity<>(rs, HttpStatus.ACCEPTED);
        } else {
            rs.setStatus(false);
            rs.setError("Something went wrong! Employee Detail is not updated");
            rs.setHttpCode("404");
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        }
    }

}
