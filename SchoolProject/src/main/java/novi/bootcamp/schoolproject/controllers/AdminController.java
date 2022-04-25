package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.services.ClassroomService;
import novi.bootcamp.schoolproject.services.UserService;
import novi.bootcamp.schoolproject.wrappers.ClassRoomCreateForm;
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


    //region User Endpoints

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

    //endregion

    //region Classroom Endpoints
    @GetMapping("/Admin/ShowRooms")
    public String showRooms(Model model)
    {

        List<Classrooms> rooms = roomService.findAllRooms();
        model.addAttribute("roomList", rooms);
        System.out.println(rooms);
        System.out.println(rooms.get(0).getClassID());
        return "/classrooms/showClassrooms";
    }

    @RequestMapping( "/Admin/RegisterClass")
    public String showRegisterClassPage(Model model)
    {
        model.addAttribute("classroom",new ClassRoomCreateForm());
        return "/classrooms/RegisterClass";
    }

    @PostMapping("/Admin/RegisterClass")
    public String saveClass(@ModelAttribute("classroom") ClassRoomCreateForm classroom)
    {
        Classrooms tempRoom = classroom.createClassRoom(classroom);
       /* userService.findTeacherByName(rooms.getTeachName());*/
/*        userService.saveUser(user);*/
        System.out.println(tempRoom.getClassID());
        System.out.println(tempRoom.getTeacher());
        return "/classrooms/RegisterClass";
    }



    //endregion


}
