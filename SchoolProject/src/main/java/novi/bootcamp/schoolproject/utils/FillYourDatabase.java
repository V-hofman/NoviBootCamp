package novi.bootcamp.schoolproject.utils;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.Student;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.repository.StudentRepository;
import novi.bootcamp.schoolproject.services.ClassroomService;
import novi.bootcamp.schoolproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FillYourDatabase implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    ClassroomService roomService;

    @Autowired
    StudentRepository studRepo;



    //Run this when springboot launches to fill with some test data
    @Override
    public void run(String... args) throws Exception {
        adminUser();
        studentUser();

        testClassroom();
    }

    //region TestData

    private void adminUser()
    {
        User user =  new User();
        user.setPassword("admin");
        user.setPersonName("admintje");
        user.setRole("admin");
        user.setId(1);
        user.setUsername("admin");

        userService.saveUser(user);
    }

    private void studentUser()
    {
        User user =  new User();
        user.setPassword("student");
        user.setPersonName("Piet");
        user.setRole("student");
        user.setId(2);
        user.setUsername("student");
        user.setPicture("test.png");
        userService.saveUser(user);

        Student student = new Student.studentBuilder()
                .user(user)
                .build();
        studRepo.save(student);
    }

    private void testClassroom()
    {
        Classrooms room = new Classrooms.roomBuilder()
                .classRoom("A13")
                .className("Wiskunde")
                .classID(1)
                .classTeacher("Mr. Pieter")
                .classDate("12-12-2021")
                .build();

        roomService.saveClassroom(room);

    }

    //endregion


}
