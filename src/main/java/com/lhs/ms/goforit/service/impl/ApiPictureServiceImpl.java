package com.lhs.ms.goforit.service.impl;

import com.lhs.ms.goforit.service.ApiPictureService;
import com.lhs.ms.goforit.utils.TencentCosUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ft.v20200304.FtClient;
import com.tencentcloudapi.ft.v20200304.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/10 22:07
 * @Description :
 */
@Service
@Slf4j
public class ApiPictureServiceImpl implements ApiPictureService {


    @Value("${com.lhs.ms.secretId}")
    private String secretId;

    @Value("${com.lhs.ms.secretKey}")
    private String secretKey;

    @Value("${com.lhs.ms.region}")
    private String region;

    @Autowired
    private TencentCosUtil tencentCosUtil;


    @Override
    public String apiPictureHandle(MultipartFile file,String fileName,String choice) {
        String urlResponse = null;
        URL imgUrl = tencentCosUtil.UploadIMG(file, fileName);
        Credential credential = new Credential(secretId,secretKey);
        FtClient ftClient = new FtClient(credential,region);
        if ("1".equals(choice)){
            urlResponse =  cartoonPic(imgUrl,credential,ftClient).getResultUrl();
        }else if("2".equals(choice)){
            urlResponse = agePic(imgUrl,credential,ftClient).getResultUrl();
        }else {
            urlResponse = swapGenderPic(imgUrl,credential,ftClient).getResultUrl();
        }
        return urlResponse;

    }

    @Override
    public String uploadPic(MultipartFile file, String fileName) {
        return tencentCosUtil.UploadIMG(file,fileName).getPath();
    }

    private FaceCartoonPicResponse cartoonPic(URL imgUrl,Credential credential,FtClient ftClient){
        FaceCartoonPicRequest req = new FaceCartoonPicRequest();
        req.setUrl(imgUrl.toString());
        req.setRspImgType("url");
        FaceCartoonPicResponse picResponse =null;
        try {
            picResponse = ftClient.FaceCartoonPic(req);
        }catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return picResponse;
    }


    private ChangeAgePicResponse agePic(URL imgUrl,Credential credential,FtClient ftClient){
        ChangeAgePicRequest req = new ChangeAgePicRequest();
        req.setUrl(imgUrl.toString());
        req.setRspImgType("url");
        AgeInfo ageInfo = new AgeInfo();
        ageInfo.setAge(Long.valueOf("30"));
        AgeInfo [] ageInfos = new AgeInfo[]{ageInfo};
        req.setAgeInfos(ageInfos);
        ChangeAgePicResponse picResponse =null;
        try {
            picResponse = ftClient.ChangeAgePic(req);
        }catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return picResponse;
    }

    private SwapGenderPicResponse swapGenderPic(URL imgUrl, Credential credential, FtClient ftClient){
        SwapGenderPicRequest req = new SwapGenderPicRequest();
        GenderInfo genderInfo = new GenderInfo();
        genderInfo.setGender(Long.valueOf("0"));
        GenderInfo [] genderInfos = new GenderInfo[]{genderInfo};
        req.setGenderInfos(genderInfos);
        req.setUrl(imgUrl.toString());
        req.setRspImgType("url");
        SwapGenderPicResponse picResponse =null;
        try {
            picResponse = ftClient.SwapGenderPic(req);
        }catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return picResponse;
    }
}
