package com.geoapp.satstock.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public Random getRandom() {
        return new Random();
    }
}
