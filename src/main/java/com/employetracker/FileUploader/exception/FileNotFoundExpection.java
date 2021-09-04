package com.employetracker.FileUploader.exception;

/**
 * @author : Vishal Srivastava
 * @Date : 04-07-2021
 **/
public class FileNotFoundExpection extends RuntimeException{

    public FileNotFoundExpection(String message) {
        super(message);
    }
}
