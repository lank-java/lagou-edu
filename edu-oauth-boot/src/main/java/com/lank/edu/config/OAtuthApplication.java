package com.lank.edu.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lank
 * @since 2021/5/16 18:34
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OAtuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAtuthApplication.class,args);
    }
}
