package com.github.wukap.tunbe.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String externalResourcesPath = System.getProperty("external.resources.path");
        log.info("External resources path: {}", externalResourcesPath);
        if (externalResourcesPath != null) {
            registry.addResourceHandler("/external/**").addResourceLocations("file:" + externalResourcesPath);
        } else {
            log.error("External resources path not set. External resources will not be served.");
        }

    }

    @Bean
    public ResourceHttpRequestHandler staticResourcesHandler() {
        return new ResourceHttpRequestHandler();
    }
}