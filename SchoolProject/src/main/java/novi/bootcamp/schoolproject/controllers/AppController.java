package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.exceptions.RoleNotFoundException;
import novi.bootcamp.schoolproject.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AppController {


    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        return "index";
    }

    @GetMapping("/Redirect")
    public String redirectPage() throws RoleNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails)
        {
            if(((UserDetails) principal).getAuthorities().toString().contains("ADMIN"))
            {
                return "redirect:/Admin";
            }
            if(((UserDetails) principal).getAuthorities().toString().contains("STUDENT"))
            {
                return "redirect:/Student";
            }
            if(((UserDetails) principal).getAuthorities().toString().contains("TEACHER"))
            {
                return "redirect:/Teacher";
            }
            if(((UserDetails) principal).getAuthorities().toString().contains("PARENT"))
            {
                return "redirect:/Parent";
            }else
            {
                throw new RoleNotFoundException("Cant find role");
            }
        }else
        {
            throw new RuntimeException("Redirect page oopsie");
        }
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
