package novi.bootcamp.schoolproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SchoolProjectApplication {

    public static void main(String[] args)
    {

        SpringApplication.run(SchoolProjectApplication.class, args);
    }


}
