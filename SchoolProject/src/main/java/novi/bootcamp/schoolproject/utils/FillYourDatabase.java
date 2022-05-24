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

import java.text.ParseException;

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

    private void testClassroom() throws ParseException {
        Classrooms room = new Classrooms.roomBuilder()
                .classRoom("A13")
                .className("Math")
                .classID(1)
                .classTeacher("Mr. Pieter")
                .classDate("2022-05-26")
                .build();

        roomService.saveClassroom(room);

        Classrooms room2 = new Classrooms.roomBuilder()
                .classRoom("B8")
                .className("Physics")
                .classID(2)
                .classTeacher("Mr. Frank")
                .classDate("2022-05-26")
                .build();

        roomService.saveClassroom(room2);

        Classrooms room3 = new Classrooms.roomBuilder()
                .classRoom("A13")
                .className("Physical Education")
                .classID(3  )
                .classTeacher("Mr. Charles")
                .classDate("2022-5-26")
                .build();

        roomService.saveClassroom(room3);

    }

    //endregion


}
