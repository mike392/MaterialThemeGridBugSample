package com.geoapp.satstock.config;

import com.geoapp.satstock.view.LabelBuilder;
import com.geoapp.satstock.view.MainViewUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UtilsConfig {
    @Bean
    @Scope("prototype")
    public LabelBuilder getLabelBuilder() {
        return new LabelBuilder();
    }

    @Bean
    @Scope("prototype")
    public MainViewUtils getMainViewUtils() {
        return new MainViewUtils();
    }
}
