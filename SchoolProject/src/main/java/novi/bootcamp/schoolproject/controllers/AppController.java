package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.models.User;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class AppController {


    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        return "index";
    }

    @GetMapping("/Admin")
    public String showAdminPage(Model model)
    {
        model.addAttribute("user", new User());
        return "admin";
    }

    @RequestMapping("/login")
    public String showLogin(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/testError")
    public void handleRequest() {
        throw new RuntimeException("test exception");
    }





}
