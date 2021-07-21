package com.employetracker.service;

import com.employetracker.Expection.DefinedExpection;
import com.employetracker.FileUploader.service.FileUploadService;
import com.employetracker.modal.Employee;
import com.employetracker.modal.ImageUpload;
import com.employetracker.repository.EmpRepository;

import com.employetracker.repository.ImageUploadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @Autowired
    private ImageUploadRepository imageUploadRepo;

    @Autowired
    private FileUploadService fileUploadService;


    @Override
    public List<Employee> allEmployees() {
        log.info("Trying to get all emp list");
        return empRepo.findAll();
    }

    @Override
    public Employee addEmp(Employee employee) {

        Employee emp = empRepo.save(employee);

        log.info("adding emp is done right here in addEmp service method");

        return empRepo.save(emp);
    }

    @Override
    public Employee getBySapId(int id) {
        Employee emp = empRepo.findBySapId(id);
        if (emp != null) {
            log.info("Getting the emp with id " + id);
            return emp;
        }
        log.error("Unable to get emp with id" + id);
        return new Employee();
    }

    @Override
    public Employee updateEmp(Employee employee) {
        Employee findEmp = empRepo.findBySapId(employee.getSapId());

        if (findEmp != null) {
            findEmp.setEmpName(employee.getEmpName().isEmpty() ? findEmp.getEmpName() : employee.getEmpName());
            findEmp.setAddress(employee.getAddress().isEmpty() ? findEmp.getAddress() : employee.getAddress());
            findEmp.setCountry(employee.getCountry().isEmpty() ? findEmp.getCountry() : employee.getCountry());
            findEmp.setDesignation(employee.getDesignation().isEmpty() ? findEmp.getDesignation() : employee.getDesignation());
            findEmp.setNumber(employee.getNumber() == 0 ? findEmp.getNumber() : employee.getNumber());
            findEmp.setImageUri(employee.getImageUri().isEmpty() ? findEmp.getImageUri() : employee.getImageUri());
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
            log.info("Successfully deleted" + sapId);
            return "Success";
        }
        log.error("Unable to delete emp with id" + sapId);
        return "Fails";
    }

    @Override
    public List<Employee> getByDesignation(String designation) {
        return empRepo.findByDesignation(designation);
    }


    @Override
    public ImageUpload uploadImage(MultipartFile file, int empId) {

        String fileName = fileUploadService.storeFile(file);

        // here ServletUriBuilder will create URL
        // fromCurrentContextPath will tell the hostname and path is appending 'userImage/downloadFile' next fileName is also appending to the end
        // http://localhost:8080/userImage/downloadFile/apc.jpg
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                // this path should be the endpoint of download api
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        // setting ImageUpload object
        ImageUpload imageObj = new ImageUpload();
        imageObj.setFileName(fileName);
        imageObj.setContentType(file.getContentType());
        imageObj.setImageUri(fileDownloadUri);
        imageUploadRepo.save(imageObj);


        // setting imageUrl in employee object
        // TODO:: we can add validation here like if it is already present then we can add something else here.
        Employee emp = empRepo.findBySapId(empId);
        emp.setImageUri(fileDownloadUri);
        empRepo.save(emp);

        return imageObj;
    }


    @Override
    public Resource downloadImage(String fileName) {
       return fileUploadService.downloadFile(fileName);
    }
}
