package com.example.donormanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC Configuration
 * Configures cache control for static resources and templates
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        // Disable caching for templates and static resources in development
        // This ensures browsers always fetch the latest version
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/templates/")
                .setCacheControl(CacheControl.noCache().mustRevalidate());
    }
}
