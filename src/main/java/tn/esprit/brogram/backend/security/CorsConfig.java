package tn.esprit.brogram.backend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/auth/**")  // Adjust the mapping as needed
                .allowedOrigins("http://localhost:4200","http://localhost:4201")
                .allowCredentials(true)
                .maxAge(3600);
    }
}