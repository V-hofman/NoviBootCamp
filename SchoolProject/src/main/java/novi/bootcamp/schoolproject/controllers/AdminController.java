package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.services.ClassroomService;
import novi.bootcamp.schoolproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClassroomService roomService;


    //Default admin choice menu
    @GetMapping("/Admin")
    public String showAdminPage(Model model)
    {
        model.addAttribute("user", new User());
        return "/admin/admin";
    }

    //region User Endpoints

    //Form page for creating a new user
    @RequestMapping( "/Admin/RegisterUser")
    public String showRegisterUserPage(Model model)
    {
        model.addAttribute("user", new User());
        return "/admin/RegisterUser";
    }

    //Posting a new user to save it in the database
    @PostMapping("/Admin/RegisterUser")
    public String savePerson(@ModelAttribute User user)
    {
        userService.saveUser(user);
        return "redirect:/Admin";
    }

    //Show the remove user form
    @RequestMapping("/Admin/RemoveUser")
    public String showRemoveUserPage(Model model)
    {
        model.addAttribute("user", new User());
        return "/admin/RemoveUser";
    }

    //Allow the user to be removed from the database
    @PostMapping("/Admin/RemoveUser")
    public String removePerson(@ModelAttribute User user)
    {
        userService.deleteUserByUsername(user);
        return "redirect:/Admin";
    }

    //Show the update user page
    @RequestMapping("/Admin/UpdateUser")
    public String showUpdateUserPage(Model model)
    {
        model.addAttribute("user", new User());
        return "/admin/UpdateUser";
    }

    //Allows the user to be updated in the database
    @PostMapping("/Admin/UpdateUser")
    public String updateUser(@ModelAttribute User user)
    {
        userService.updateUser(user);
        return "redirect:/Admin";
    }

    //Shows the page with all users
    @GetMapping("/Admin/ShowUsers")
    public String showUsers(Model model)
    {

        List<User> users = userService.findAllUsers();
        model.addAttribute("userlist", users);
        return "/admin/ShowUsers";
    }

    //endregion

    //region Classroom Endpoints

    //Shows the page with all classrooms
    @GetMapping("/Admin/ShowRooms")
    public String showRooms(Model model)
    {

        List<Classrooms> rooms = roomService.findAllRooms();
        model.addAttribute("roomList", rooms);
        return "/classrooms/showClassrooms";
    }

    //Shows the page with creating a new class form
    @RequestMapping( "/Admin/RegisterClass")
    public String showRegisterClassPage(Model model)
    {
        model.addAttribute("classroom", new Classrooms());
        return "/classrooms/RegisterClass";
    }

    //Allows the class to be saved in the database
    @PostMapping("/Admin/RegisterClass")
    public String saveClass(@ModelAttribute("classroom") Classrooms classroom)
    {
        roomService.saveClassroom(classroom);
        return "redirect:/Admin";
    }

    //Shows the remove class page
    @GetMapping("/Admin/RemoveClass")
    public String showRemoveClassPage(Model model)
    {
        model.addAttribute("classroom", new Classrooms());
        return "/classrooms/RemoveClass";
    }

    //Allows the class to be removed from the database
    @PostMapping("/Admin/RemoveClass")
    public String removeClass(@ModelAttribute Classrooms room)
    {
        roomService.removeClassroomByID(room.getClassID());
        return "redirect:/Admin";
    }



    //endregion


}
