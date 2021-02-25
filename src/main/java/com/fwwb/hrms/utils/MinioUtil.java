package com.fwwb.hrms.utils;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: 周余民
 * @Date: Created in 14:33 2021/2/25
 * @Description: 封装minio常用方法
 */
@Component
public class MinioUtil {
    @Resource
    MinioClient minioClient;

    @Value("${minio.bucket-name}")
    String bucketName;

    /**
     * 存储对象
     * @param originFileName 源文件名
     * @param stream 数据流
     * @param contentType 数据类型
     * @return 存储的对象名
     * @throws Exception 异常
     */
    public String putObject(String originFileName, InputStream stream, String contentType) throws Exception {
        //根据uuid生成新的对象名
        String objectName = UUID.randomUUID().toString().replaceAll("-", "") +
                originFileName.substring(originFileName.lastIndexOf("."));
        //minio存储对象
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                stream, stream.available(), -1)
                .contentType(contentType)
                .build());
        return objectName;
    }
}