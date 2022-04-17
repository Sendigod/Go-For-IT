package com.lhs.ms.goforit.controller;

import com.lhs.ms.goforit.service.ApiPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/16 16:27
 * @Description :
 */
@RestController
public class PicController {

    @Autowired
    private ApiPictureService apiPictureService;


    @PostMapping(value = "/upload-test")
    public ResponseEntity picToCos(@RequestParam MultipartFile file, @RequestParam String imgName){
        return ResponseEntity.ok(apiPictureService.uploadPic(file,imgName));
    }

    @PostMapping(value = "/cartoon-test")
    public String  generatePic(@RequestParam MultipartFile file, @RequestParam String imgName,@RequestParam String choice){
        return apiPictureService.apiPictureHandle(file,imgName,choice);
    }
}
