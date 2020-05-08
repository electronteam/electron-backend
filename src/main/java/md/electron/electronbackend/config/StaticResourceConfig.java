package md.electron.electronbackend.config;

import md.electron.electronbackend.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer
{
    @Value("${data.media.dir}")
    private String mediaFolder;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry)
    {
        final String projectRootPath = Utils.getProjectRootPath();
        registry.addResourceHandler("/**")
                .addResourceLocations(String.format("file:%s%s", projectRootPath, this.mediaFolder), "classpath:/static/");
    }
}
