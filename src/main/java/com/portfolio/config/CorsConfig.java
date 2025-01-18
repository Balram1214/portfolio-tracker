package com.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow CORS on all endpoints
                        .allowedOrigins("http://localhost:3000") // Replace with frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Include all required methods
                        .allowedHeaders("*") // Allow all headers
                        .exposedHeaders("Authorization", "Link", "X-Total-Count") // Expose specific headers, if needed
                        .allowCredentials(true); // Support credentials like cookies or authorization headers
            }
        };
    }
}
