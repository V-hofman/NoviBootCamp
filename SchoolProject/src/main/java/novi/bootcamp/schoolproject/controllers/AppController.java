package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.exceptions.RoleNotFoundException;
import novi.bootcamp.schoolproject.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppController {

    //Shows the homepage
    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        return "index";
    }

    //Redirect to the proper page based on the current logged in user's role
    @GetMapping("/Redirect")
    public String redirectPage() throws RoleNotFoundException {

        //Grab the current logged-in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //See if it's an actual user
        if(principal instanceof UserDetails)
        {
            //If the authorities contain ADMIN send them to the ADMIN page
            if(((UserDetails) principal).getAuthorities().toString().contains("ADMIN"))
            {
                return "redirect:/Admin";
            }
            //If the authorities contain STUDENT send them to the STUDENT page
            if(((UserDetails) principal).getAuthorities().toString().contains("STUDENT"))
            {
                return "redirect:/Student";
            }
            //If for some reason another roll is there throw error
            else
            {
                throw new RoleNotFoundException("Cant find role");
            }
            //If it's not a user throw another error
        }else
        {
            throw new RuntimeException("Redirect page oopsie");
        }
    }

    //Show the login page
    @RequestMapping("/login")
    public String showLogin(Model model)
    {
        //Add a user to the model, so we can send it with the POST request
        model.addAttribute("user", new User());

        return "login";
    }

    //Show a test error page
    @RequestMapping("/testError")
    public void handleRequest() {
        throw new RuntimeException("test exception");
    }





}
