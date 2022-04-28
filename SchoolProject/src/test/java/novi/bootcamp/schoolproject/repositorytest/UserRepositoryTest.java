package novi.bootcamp.schoolproject.repositorytest;

import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    private User user = createTestUser();


    @Test
    @Order(1)
    public void testCreateUser()
    {
        User savedUser = repo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(existUser.getUsername().equals(savedUser.getUsername()));
    }

    @Test
    @Order(2)
    public void testFindByUsername()
    {
       user = repo.findByUsername(user.getUsername());
       assertThat(user).isNotNull();
    }

    @Test
    @Order(3)
    public void TestDeleteUser()
    {
        repo.deleteByUsername(user.getUsername());
        assertThat(repo.findByUsername(user.getUsername())).isNull();
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
