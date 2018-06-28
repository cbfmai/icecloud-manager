package com.mmtech.icecloud.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ADAM
 */
@EnableTransactionManagement
@SpringBootApplication
public class IceCloudManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IceCloudManagerApplication.class, args);
    }

}
