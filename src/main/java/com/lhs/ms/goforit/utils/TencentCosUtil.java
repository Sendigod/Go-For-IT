package com.lhs.ms.goforit.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.ciModel.auditing.ImageAuditingRequest;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/16 16:16
 * @Description :
 */
@Component
public class TencentCosUtil {


    @Value("${com.lhs.ms.secretId}")
    private String secretId;

    @Value("${com.lhs.ms.secretKey}")
    private String secretKey;

    @Value("${com.lhs.ms.region}")
    private String region;

    @Value("${com.lhs.ms.cos.bucketName}")
    public String bucketName;

    public URL UploadIMG(MultipartFile imgFile, String imgName) {
        try {
            InputStream inputStream = imgFile.getInputStream();
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // - 使用输入流存储，需要设置请求长度
            objectMetadata.setContentLength(inputStream.available());
            // - 设置缓存
            objectMetadata.setCacheControl("no-cache");
            // - 设置Content-Type
            // 指定文件将要存放的存储桶
            // 指定文件上传到 COS 上的路径，即对象键。
            String key = "imageHost/" + imgName;
            COSClient cosClient = getCosClient();
            cosClient.putObject(bucketName, key, inputStream, objectMetadata);
            return cosClient.getObjectUrl(bucketName,key);
        } catch (Exception e) {
            e.printStackTrace();
            // 发生IO异常、COS连接异常等，返回空
            return null;
        }
    }
//    public String judgeIMG() {
//        //1.创建任务请求对象
//        ImageAuditingRequest request = new ImageAuditingRequest();
//        //2.添加请求参数 参数详情请见api接口文档
//        //2.1设置请求bucket
//        request.setBucketName(bucketName);
//        //2.2设置审核类型
//        request.setDetectType("porn");
//        //2.3设置bucket中的图片位置
//        request.setObjectKey("imageHost/动物-鸟.jpg");
//        //3.调用接口,获取任务响应对象
//        ImageAuditingResponse response = getCosClient().imageAuditing(request);
//        return response.getPornInfo().getHitFlag();
//    }


    private COSClient getCosClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2.1 设置存储桶的地域（上文获得）
        Region realRegion = new Region(region);
        ClientConfig clientConfig = new ClientConfig(realRegion);
        // 2.2 使用https协议传输
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 返回COS客户端
        return cosClient;
    }
}
