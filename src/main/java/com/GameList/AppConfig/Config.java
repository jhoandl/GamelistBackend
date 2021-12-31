package com.GameList.AppConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Config implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // el  eslas y doble ** (/**) especifica que todos los metodos que encuentre les de permiso
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
    }

}
