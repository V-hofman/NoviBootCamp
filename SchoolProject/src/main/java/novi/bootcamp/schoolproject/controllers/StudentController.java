package novi.bootcamp.schoolproject.controllers;

import com.lowagie.text.DocumentException;
import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.services.ClassroomService;
import novi.bootcamp.schoolproject.services.UserService;
import novi.bootcamp.schoolproject.utils.FileUploadUtil;
import novi.bootcamp.schoolproject.utils.PDFExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClassroomService roomService;

    //region Endpoints for student

    //Show default student choice menu
    @RequestMapping("/Student")
    public String showStudentMenuChoice(Model model)
    {
        //Add a user to the model
        model.addAttribute("user", new User());

        return "/students/student";
    }

    //Show the details of the student
    @RequestMapping("/Student/ShowDetails")
    public String showStudentDetailPage(Model model)
    {
        //Grab the current logged-in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userService.getUserByUserName(username);

        //Add the user we grabbed to the model, so we can see the details
        model.addAttribute("user", user);

        return "/students/showStudentDetails";
    }

    //Allows the student to upload an image
    @PostMapping("/Student/ShowDetails")
    public String saveStudentPicture(@RequestParam("image")MultipartFile multipartFile)
            throws IOException
    {
        //Grab the path name from the file we upload
        String filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        //Grab the user that is currently logged-in
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userService.getUserByUserName(username);

        //Set the picture to the current logged-in user and update the Database
        user.setPicture(filename);
        userService.updateImage(user);

        //Make a string for the file location
        String uploadDir = "user-photos/" + user.getId();

        //Send the needed data to the file saving class
        FileUploadUtil.saveFile(uploadDir, filename, multipartFile);

        return "redirect:/Student";
    }

    //endregion

    //Shows the page with all classrooms
    @GetMapping("/Student/ShowRooms")
    public String showRooms(Model model)
    {
        //Grab a list of all classrooms
        List<Classrooms> rooms = roomService.findAllRooms();

        //Add the list to the model, so we can display them
        model.addAttribute("roomList", rooms);

        return "/classrooms/showClassrooms";
    }

    //Exports the current classrooms to a downloadable PDF file
    @GetMapping("/Student/export/pdf")
    public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException
    {
        //Set the response as a PDF file
        response.setContentType("application/pdf");

        //Create a String from the date and set it to the current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        //Not too sure what the headerkey does, but its needed :), headerValue is the name of the file and that its an attachment
        String headerKey = "Content-Disposition";
        String headerValue= "attachment; filename=classes_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        //Grab a list of all classes and send it to the exporter that makes it into a PDF file
        List<Classrooms> roomList = roomService.findAllRooms();
        PDFExporter exporter = new PDFExporter(roomList);
        exporter.export(response);
    }
}
