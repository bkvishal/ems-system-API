package com.employetracker.FileUploader.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Vishal Srivastava
 * @Date : 04-07-2021
 **/

public interface FileUploadService {

    String storeFile(MultipartFile file);

    Resource downloadFile(String fileName);
}
