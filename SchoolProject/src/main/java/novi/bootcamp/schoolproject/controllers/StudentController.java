package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.services.ClassroomService;
import novi.bootcamp.schoolproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClassroomService roomService;

    //region Endpoints for student

    @RequestMapping("/Student")
    public String showStudentMenuChoice(Model model)
    {
        model.addAttribute("user", new User());
        return "/students/student";
    }

    @RequestMapping("/Student/ShowDetails")
    public String showStudentDetailPage(Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userService.getUserByUserName(username);
        model.addAttribute("user", user);
        return "/students/showStudentDetails";
    }

    @RequestMapping("/Student/UpdateUser")
    public String showUpdateUserPage(Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userService.getUserByUserName(username);
        model.addAttribute("user", user);
        return "/students/UpdateUser";
    }

    @PostMapping("/Student/UpdateUser")
    public String updateUser(@ModelAttribute User user)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        System.out.println(username);
        System.out.println(user.getUsername());
        if(user.getUsername().equals(username))
        {
            System.out.println("good one");

            userService.updateUser(user);
        }
        return "redirect:/Student";
    }
    //endregion
}
