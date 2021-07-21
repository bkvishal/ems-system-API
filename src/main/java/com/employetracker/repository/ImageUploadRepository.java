package com.employetracker.repository;

import com.employetracker.modal.ImageUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Vishal Srivastava
 * @Date : 04-07-2021
 **/

@Repository
public interface ImageUploadRepository extends JpaRepository<ImageUpload, Long> {
}
