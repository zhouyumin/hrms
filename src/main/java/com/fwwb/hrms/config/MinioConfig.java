package com.fwwb.hrms.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 周余民
 * @Date: Created in 14:32 2021/2/25
 * @Description: minio配置
 */
@Configuration
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value(("${minio.bucket-name}"))
    private String bucketName;

    /**
     * 注入MinIo
     *
     * @return MinIo连接对象
     */
    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
        boolean exist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        //bucket不存在时创建一个
        if (!exist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
        return minioClient;
    }
}