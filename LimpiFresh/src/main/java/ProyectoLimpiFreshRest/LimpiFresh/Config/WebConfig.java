package ProyectoLimpiFreshRest.LimpiFresh.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Permite acceder a las fotos en la carpeta "uploads" desde la URL
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}