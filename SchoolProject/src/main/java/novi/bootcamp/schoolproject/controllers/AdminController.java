package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private UserService userService;



    @RequestMapping( "/Admin/RegisterUser")
    public String showRegisterUserPage(Model model)
    {
        model.addAttribute("user", new User());
        return "RegisterUser";
    }

    @PostMapping("/Admin/RegisterUser")
    public String savePerson(@ModelAttribute User user)
    {
        userService.saveUser(user);
        return "RegisterUser";
    }

    @RequestMapping("/Admin/RemoveUser")
    public String showRemoveUserPage(Model model)
    {
        model.addAttribute("user", new User());
        return "RemoveUser";
    }

    @PostMapping("/Admin/RemoveUser")
    public String removePerson(@ModelAttribute User user)
    {
        userService.deleteUserByUsername(user);
        return "RemoveUser";
    }

    @RequestMapping("/Admin/UpdateUser")
    public String showUpdateUserPage(Model model)
    {
        model.addAttribute("user", new User());
        return "UpdateUser";
    }

    @PostMapping("/Admin/UpdateUser")
    public String updateUser(@ModelAttribute User user)
    {
        userService.updateUser(user);
        return "UpdateUser";
    }

    @GetMapping("/Admin/ShowUsers")
    public String showUsers(Model model)
    {

        List<User> users = userService.findAllUsers();
        model.addAttribute("userlist", users);
        return "ShowUsers";
    }


}
