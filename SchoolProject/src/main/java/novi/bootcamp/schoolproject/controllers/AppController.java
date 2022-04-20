package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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





}
