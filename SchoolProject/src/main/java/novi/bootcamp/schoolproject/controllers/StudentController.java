package novi.bootcamp.schoolproject.controllers;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.services.ClassroomService;
import novi.bootcamp.schoolproject.services.UserService;
import novi.bootcamp.schoolproject.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @PostMapping("/Student/ShowDetails")
    public String saveStudentPicture(                                     @RequestParam("image")MultipartFile multipartFile)
            throws IOException
    {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userService.getUserByUserName(username);
        user.setPicture(filename);

        userService.updateImage(user);

        User savedUser = userService.getUserByUserName(user.getUsername());

        String uploadDir = "user-photos/" + savedUser.getId();

        FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
        return "redirect:/Student";
    }


    //endregion

    @GetMapping("/Student/ShowRooms")
    public String showRooms(Model model)
    {

        List<Classrooms> rooms = roomService.findAllRooms();
        model.addAttribute("roomList", rooms);
        return "/classrooms/showClassrooms";
    }
}
