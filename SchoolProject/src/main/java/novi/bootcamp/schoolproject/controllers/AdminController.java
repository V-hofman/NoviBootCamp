package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.services.ClassroomService;
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

    @Autowired
    private ClassroomService roomService;


    @GetMapping("/Admin")
    public String showAdminPage(Model model)
    {
        model.addAttribute("user", new User());
        return "/admin/admin";
    }

    //region User Endpoints

    @RequestMapping( "/Admin/RegisterUser")
    public String showRegisterUserPage(Model model)
    {
        model.addAttribute("user", new User());
        return "/admin/RegisterUser";
    }

    @PostMapping("/Admin/RegisterUser")
    public String savePerson(@ModelAttribute User user)
    {
        userService.saveUser(user);
        return "redirect:/Admin";
    }

    @RequestMapping("/Admin/RemoveUser")
    public String showRemoveUserPage(Model model)
    {
        model.addAttribute("user", new User());
        return "/admin/RemoveUser";
    }

    @PostMapping("/Admin/RemoveUser")
    public String removePerson(@ModelAttribute User user)
    {
        userService.deleteUserByUsername(user);
        return "redirect:/Admin";
    }

    @RequestMapping("/Admin/UpdateUser")
    public String showUpdateUserPage(Model model)
    {
        model.addAttribute("user", new User());
        return "/admin/UpdateUser";
    }

    @PostMapping("/Admin/UpdateUser")
    public String updateUser(@ModelAttribute User user)
    {
        userService.updateUser(user);
        return "redirect:/Admin";
    }

    @GetMapping("/Admin/ShowUsers")
    public String showUsers(Model model)
    {

        List<User> users = userService.findAllUsers();
        model.addAttribute("userlist", users);
        return "/admin/ShowUsers";
    }

    //endregion

    //region Classroom Endpoints
    @GetMapping("/Admin/ShowRooms")
    public String showRooms(Model model)
    {

        List<Classrooms> rooms = roomService.findAllRooms();
        model.addAttribute("roomList", rooms);
        return "/classrooms/showClassrooms";
    }

    @RequestMapping( "/Admin/RegisterClass")
    public String showRegisterClassPage(Model model)
    {
        model.addAttribute("classroom", new Classrooms());
        return "/classrooms/RegisterClass";
    }

    @PostMapping("/Admin/RegisterClass")
    public String saveClass(
            @ModelAttribute("classroom") Classrooms classroom
            )
    {
        roomService.saveClassroom(classroom);
        return "redirect:/Admin";
    }

    @GetMapping("/Admin/RemoveClass")
    public String showRemoveClassPage(Model model)
    {
        model.addAttribute("classroom", new Classrooms());
        return "/classrooms/RemoveClass";
    }

    @PostMapping("/Admin/RemoveClass")
    public String removeClass(@ModelAttribute Classrooms room)
    {
        roomService.removeClassroomByID(room.getClassID());
        return "redirect:/Admin";
    }



    //endregion


}
