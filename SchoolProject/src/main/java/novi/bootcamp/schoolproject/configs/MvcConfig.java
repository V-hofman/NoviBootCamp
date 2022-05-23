package novi.bootcamp.schoolproject.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //We need to add a directory, that way we can find the images we added
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        exposeDirectory("user-photos", registry);
    }

    //Then we expose the directory, so spring can send them to thymeleaf
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry)
    {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if(dirName.startsWith("../")) dirName = dirName.replace("../", "");

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
    }
}
