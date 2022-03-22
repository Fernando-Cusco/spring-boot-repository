package ec.edu.ups.springbootdatajpa;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        String resourcesPath = Paths.get("uploads").toAbsolutePath().toUri().toString();
        log.info(resourcesPath);
        registry.addResourceHandler("/uploads/**")
        .addResourceLocations(resourcesPath);
    }
}
