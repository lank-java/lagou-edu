package com.lank.edu.boss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LanceLan
 * @since 2021/5/12 16:24
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.lank.edu")
public class EduBossApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduBossApplication.class,args);
    }
}
