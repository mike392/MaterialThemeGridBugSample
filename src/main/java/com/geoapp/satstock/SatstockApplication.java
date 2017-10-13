package com.geoapp.satstock;

import com.vaadin.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableVaadin
@SpringBootApplication
public class SatstockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatstockApplication.class, args);
    }
}
