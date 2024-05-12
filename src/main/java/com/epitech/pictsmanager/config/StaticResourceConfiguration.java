package com.epitech.pictsmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = "file:./photosData/";
        registry.addResourceHandler("/photosData/**")
                .addResourceLocations(path)
                .setCachePeriod(0);
    }
}