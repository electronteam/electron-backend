package md.electron.electronbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class ElectronBackendApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ElectronBackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(final CorsRegistry registry)
            {
                registry.addMapping("/**")
                        .allowCredentials(true);
            }
        };
    }
}
