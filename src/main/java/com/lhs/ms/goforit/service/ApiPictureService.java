package com.lhs.ms.goforit.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/10 22:06
 * @Description :
 */
public interface ApiPictureService {

    String apiPictureHandle(MultipartFile file, String fileName,String choice);

    String uploadPic(MultipartFile file, String fileName);
}
