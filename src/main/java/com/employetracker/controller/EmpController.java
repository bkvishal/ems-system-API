package com.employetracker.controller;

import com.employetracker.Expection.UserNotFoundException;
import com.employetracker.commonEntity.Response;
import com.employetracker.modal.Employee;
import com.employetracker.modal.ImageUpload;
import com.employetracker.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.Collections;
import java.util.List;

/**
 * @author : Vishal Srivastava
 * @Date : 22-08-2020
 **/

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;


    @PostMapping(value = "/checking")
    public String exceptionCheck(@ModelAttribute Employee employee) {
       int i=10;

       // using modelAttribute we are getting object values as parameters in the URL;
        System.out.println(employee);

       int j = i/0;

        return "Success";
    }

    @RequestMapping(value = "/getHead", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> getHeader(){
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH)
                .build();
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Response> getEmployee() {
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
    public ResponseEntity<Response> findEmpBySapId(@PathVariable("SAP") int sapId) {
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
        if (result.equals("Success")) {
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

    @GetMapping(value = "/getByDesign/{designation}")
    public ResponseEntity<Response> getByDesignation(@PathVariable("designation") String designation) {
        List<Employee> result = empService.getByDesignation(designation);
        Response rs = new Response();

        if (!result.isEmpty()) {
            rs.setHttpCode("200");
            rs.setStatus(true);
            rs.setResult(result);
            return new ResponseEntity<>(rs, HttpStatus.OK);
        } else {
            rs.setStatus(false);
            rs.setError("Something went wrong! Employee with chosen designation not fetched");
            rs.setHttpCode("404");
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/imageUpload/{empId}")
    public ResponseEntity<Response> uploadEmpImage(@RequestParam("file") MultipartFile file, @PathVariable int empId) {

        ImageUpload result = empService.uploadImage(file, empId);
        Response rs = new Response();

        if (result != null) {
            rs.setHttpCode("200");
            rs.setStatus(true);
            rs.setResult(Collections.singletonList(result));
            return new ResponseEntity<>(rs, HttpStatus.OK);
        } else {
            rs.setStatus(false);
            rs.setError("Something went wrong! Image is not being uploaded");
            rs.setHttpCode("404");
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadEmpImage(@PathVariable("fileName") String fileName, HttpServletRequest request) {

        // Loading file as a resource
        Resource resource = empService.downloadImage(fileName);

        // checking the file content type
        String contentType = null;

        // when we want to render any file type then we user MimeType .. this include every content type rather then only image or pdf
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        } catch (IOException io) {
            log.error("Unable to find content type");
            io.printStackTrace();
        }

        if (contentType ==null) {
            contentType =MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                // ---- this attachment; filename will download the file when URL is hit
                //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + resource.getFilename())
                // this inline will render the image in the browser
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=" + resource.getFilename())
                .body(resource);
    }

}
