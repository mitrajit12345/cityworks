package com.cityworks.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "CityWorks API",
        version = "1.0",
        description = "Operations related to task creation, retrieval, and updates within the CityWorks system."
))
public class SwaggerConfig {
}
