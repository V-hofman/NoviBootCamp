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

import java.text.ParseException;
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
        //Add a user to the model, so we can send it with the POST request
        model.addAttribute("user", new User());

        return "/admin/admin";
    }

    //region User Endpoints

    //Form page for creating a new user
    @RequestMapping( "/Admin/RegisterUser")
    public String showRegisterUserPage(Model model)
    {
        //Add a user to the model, so we can send it with the POST request
        model.addAttribute("user", new User());

        return "/admin/RegisterUser";
    }

    //Posting a new user to save it in the database
    @PostMapping("/Admin/RegisterUser")
    public String savePerson(@ModelAttribute User user)
    {
        //Try to save the user we received in the POST
        userService.saveUser(user);

        return "redirect:/Admin";
    }

    //Show the remove user form
    @RequestMapping("/Admin/RemoveUser")
    public String showRemoveUserPage(Model model)
    {
        //Add a user to the model, so we can send it with the POST request
        model.addAttribute("user", new User());

        return "/admin/RemoveUser";
    }

    //Allow the user to be removed from the database
    @PostMapping("/Admin/RemoveUser")
    public String removePerson(@ModelAttribute User user)
    {
        //Try to delete the user
        userService.deleteUserByUsername(user);

        return "redirect:/Admin";
    }

    //Show the update user page
    @RequestMapping("/Admin/UpdateUser")
    public String showUpdateUserPage(Model model)
    {
        //Add a user to the model, so we can send it with the POST request
        model.addAttribute("user", new User());

        return "/admin/UpdateUser";
    }

    //Allows the user to be updated in the database
    @PostMapping("/Admin/UpdateUser")
    public String updateUser(@ModelAttribute User user)
    {
        //Try to update the user
        userService.updateUser(user);

        return "redirect:/Admin";
    }

    //Shows the page with all users
    @GetMapping("/Admin/ShowUsers")
    public String showUsers(Model model)
    {
        //Create a list of users from the users we find in the Database
        List<User> users = userService.findAllUsers();

        //Add a list of users to the model, that way we can display it
        model.addAttribute("userlist", users);
        return "/admin/ShowUsers";
    }

    //endregion

    //region Classroom Endpoints

    //Shows the page with all classrooms
    @GetMapping("/Admin/ShowRooms")
    public String showRooms(Model model)
    {
        //Create a list of classrooms from the Database
        List<Classrooms> rooms = roomService.findAllRooms();

        //Add a list of classrooms to the model, so we can display it
        model.addAttribute("roomList", rooms);

        return "/classrooms/showClassrooms";
    }

    //Shows the page with creating a new class form
    @RequestMapping( "/Admin/RegisterClass")
    public String showRegisterClassPage(Model model)
    {
        //Add a classroom to the model, so we can send it with the POST request
        model.addAttribute("classroom", new Classrooms());

        return "/classrooms/RegisterClass";
    }

    //Allows the class to be saved in the database
    @PostMapping("/Admin/RegisterClass")
    public String saveClass(@ModelAttribute("classroom") Classrooms classroom) throws ParseException {

        //Try to save the classroom to the Database
        roomService.saveClassroom(classroom);

        return "redirect:/Admin";
    }

    //Shows the remove class page
    @GetMapping("/Admin/RemoveClass")
    public String showRemoveClassPage(Model model)
    {
        //Add a classroom to the model, so we can send it with the POST request
        model.addAttribute("classroom", new Classrooms());

        return "/classrooms/RemoveClass";
    }

    //Allows the class to be removed from the database
    @PostMapping("/Admin/RemoveClass")
    public String removeClass(@ModelAttribute Classrooms room)
    {
        //Try to remove the classroom using the ID, since multiple classes can have the same name
        roomService.removeClassroomByID(room.getClassID());

        return "redirect:/Admin";
    }
    //endregion
}
