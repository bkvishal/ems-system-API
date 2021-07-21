package com.employetracker.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : Vishal Srivastava
 * @Date : 04-07-2021
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_upload")
public class ImageUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private String contentType;
    private String imageUri;

}
