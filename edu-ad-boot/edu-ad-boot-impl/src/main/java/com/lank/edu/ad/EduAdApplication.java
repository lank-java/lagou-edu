package com.lank.edu.ad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LanceLan
 * @since 2021/5/12 16:02
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lank.edu.ad.mapper")
public class EduAdApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduAdApplication.class,args);
    }
}
