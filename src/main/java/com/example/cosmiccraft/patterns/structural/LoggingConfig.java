package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.repositories.LogRepository;
import com.example.cosmiccraft.services.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @Bean
    public DatabaseLoggingAdapter productServiceLogger(LogRepository logRepository) {
        return new DatabaseLoggingAdapter(
                LoggerFactory.getLogger(ProductService.class),
                logRepository,
                ProductService.class.getName()
        );
    }
}
