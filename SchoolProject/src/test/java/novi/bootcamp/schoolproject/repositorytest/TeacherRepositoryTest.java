package novi.bootcamp.schoolproject.repositorytest;

import novi.bootcamp.schoolproject.models.Teacher;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.repository.TeacherRepository;
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
public class TeacherRepositoryTest {


    @Autowired
    private TeacherRepository teacherRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TestEntityManager entityManager;
    private User user = createTestUser();
    private Teacher teacher = createTestTeacher();


    @Test
    @Order(1)
    public void testCreateTeacher()
    {
        Teacher savedTeacher = teacherRepo.save(teacher);
        Teacher existTeacher = entityManager.find(Teacher.class, savedTeacher.getTeachID());
        assertThat(existTeacher.getTeachID() == savedTeacher.getTeachID());

    }

    @Test
    @Order(2)
    public void testFindByPersonName()
    {
        teacher = teacherRepo.findByName(teacher.getUser().getPersonName());
        assertThat(teacher).isNotNull();
    }
    @Test
    @Order(3)
    public void testFindByUserID()
    {
        user.setId(teacherRepo.findByName(teacher.getUser().getPersonName()).getUser().getId());
        teacher = teacherRepo.findByUserID(user.getId());
        assertThat(teacher).isNotNull();
    }

    @Test
    @Order(4)
    public void testDeleteTeacher()
    {
        user = userRepo.findByUsername(user.getUsername());
        teacherRepo.deleteByUserID(user.getId());
        assertThat(teacherRepo.findByUserID(teacher.getUser().getId())).isNull();

    }
    @Test
    @Order(5)
    public void testDeleteUser()
    {
        userRepo.deleteByUsername(teacher.getUser().getUsername());
        assertThat(userRepo.findByUsername(teacher.getUser().getUsername())).isNull();

    }

    public Teacher createTestTeacher()
    {
        return new Teacher.TeacherBuilder()
                .user(user)
                .build();
    }

    public User createTestUser()
    {
        User user = new User();
        user.setPassword("Password");
        user.setUsername("Derik12");
        user.setPersonName("Derik Shoen");
        user.setRole("Teacher");
        return user;
    }

}
