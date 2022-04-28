package novi.bootcamp.schoolproject.repositorytest;

import novi.bootcamp.schoolproject.models.Student;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.repository.StudentRepository;
import novi.bootcamp.schoolproject.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TestEntityManager entityManager;

    private User user = createTestUser();
    private Student student = createTestStudent();


    @Test
    @Order(1)
    public void testCreateStudent()
    {
        Student savedStudent = studRepo.save(student);
        Student existStudent = entityManager.find(Student.class, student.getStudentID());
        assertThat(existStudent.getStudentID() == savedStudent.getStudentID());
    }

    @Test
    @Order(2)
    public void testFindByUserID()
    {
        user.setId(studRepo.findByUsername(user.getUsername()).getUser().getId());
        student = studRepo.findByUserID(user.getId());
        assertThat(student).isNotNull();
    }

    @Test
    @Order(3)
    public void testDeleteStudentByUserID()
    {
        user = userRepo.findByUsername(user.getUsername());
        studRepo.deleteByUserID(user.getId());
        assertThat(studRepo.findByUserID(user.getId())).isNull();
    }

    @Test
    @Order(4)
    public void testDeleteUserByUsername()
    {
        userRepo.deleteByUsername(student.getUser().getUsername());
        assertThat(userRepo.findByUsername(student.getUser().getUsername())).isNull();
    }


    public Student createTestStudent()
    {
        return new Student.studentBuilder()
                .user(user)
                .build();
    }

    public User createTestUser()
    {
        User user = new User();
        user.setPassword("Password");
        user.setUsername("Derik12");
        user.setPersonName("Derik Shoen");
        user.setRole("Student");
        return user;
    }


}
