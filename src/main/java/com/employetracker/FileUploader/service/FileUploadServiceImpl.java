package com.employetracker.FileUploader.service;

import com.employetracker.FileUploader.exception.FileNotFoundExpection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

/**
 * @author : Vishal Srivastava
 * @Date : 04-07-2021
 **/

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    private Path fileStoragePath;

    // @Value("${file.storage.location:temp}") set the location (directory) where file will be storage if there is no path then it will store in default location (that's why :temp)
    public FileUploadServiceImpl(@Value("${file.storage.location:temp}") String fileLocation) {
        fileStoragePath = Paths.get(fileLocation).toAbsolutePath().normalize();

        try {
            log.info("inside constructor of FileUploadServiceImpl and now creating directory if it is not exists");
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            log.error("something wrong wrong with the directory where file is uploading");
            e.printStackTrace();
        }
    }

    @Override
    public String storeFile(MultipartFile file) {

        log.info("Inside storeFile method and file name before cleanPath is used " + file);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("Inside storeFile method and file name after cleanPath is used " + file);
        try {
            Path targetLocation = fileStoragePath.resolve(fileName);
            log.info("starting the file copying to targetLocation " + targetLocation);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error("something went wrong .. copy is not done!");
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public Resource downloadFile(String fileName) {
        try {
            // like checking the file in the location and resolving the file
            Path path = this.fileStoragePath.resolve(fileName).normalize();
            log.info("Inside downloadFile method and file name after resolve and normalize is used " + fileName);

            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundExpection("The file you are looking is not available");
            }

        } catch (MalformedURLException e) {
            throw new FileNotFoundExpection("Something went wrong...with you file");
        }
    }
}
