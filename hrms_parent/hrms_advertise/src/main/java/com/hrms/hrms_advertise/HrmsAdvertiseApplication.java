package com.hrms.hrms_advertise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HrmsAdvertiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmsAdvertiseApplication.class, args);
    }

}