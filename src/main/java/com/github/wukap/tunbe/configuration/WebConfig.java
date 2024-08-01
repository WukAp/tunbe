package com.github.wukap.tunbe.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String externalResourcesPath = System.getProperty("external.resources.path");
System.out.println(externalResourcesPath);
        if (externalResourcesPath != null) {
            registry.addResourceHandler("/external/**")
                    .addResourceLocations("file:" + externalResourcesPath);
        }
        else {
            System.out.println("External resources path not set. External resources will not be served.");
        }

    }

    @Bean
    public ResourceHttpRequestHandler staticResourcesHandler() {
        return new ResourceHttpRequestHandler();
    }
}