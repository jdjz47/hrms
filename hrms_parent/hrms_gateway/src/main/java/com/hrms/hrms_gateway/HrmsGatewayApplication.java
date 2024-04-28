package com.hrms.hrms_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HrmsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmsGatewayApplication.class, args);
    }

}
