package com.fwwb.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author: 周余民
 * @Date: Created in 23:50 2021/1/22
 */
@SpringBootApplication
@EnableSwagger2
public class HrmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrmsApplication.class, args);
    }
}
