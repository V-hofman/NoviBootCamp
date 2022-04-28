package novi.bootcamp.schoolproject.repositorytest;

import novi.bootcamp.schoolproject.models.Parent;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.repository.ParentRepository;
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
public class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TestEntityManager entityManager;

    private User user = createTestUser();
    private Parent parent = createTestParent();


    @Test
    @Order(1)
    public void testCreateParent()
    {
        Parent savedParent = parentRepo.save(parent);
        Parent existParent = entityManager.find(Parent.class, parent.getParentID());
        assertThat(existParent.getParentID() == savedParent.getParentID());
    }

    @Test
    @Order(2)
    public void testFindByUserID()
    {
        user.setId(parentRepo.findByName(user.getPersonName()).getUser().getId());
        parent = parentRepo.findByUserID(user.getId());
        assertThat(parent).isNotNull();
    }

    @Test
    @Order(3)
    public void testDeleteParentByUserID()
    {
        user = userRepo.findByUsername(user.getUsername());
        parentRepo.deleteByUserID(user.getId());
        assertThat(parentRepo.findByUserID(user.getId())).isNull();
    }

    @Test
    @Order(4)
    public void testDeleteUserByUsername()
    {
        userRepo.deleteByUsername(parent.getUser().getUsername());
        assertThat(userRepo.findByUsername(parent.getUser().getUsername())).isNull();
    }

    public Parent createTestParent()
    {
        return new Parent.parentBuilder()
                .User(user)
                .build();
    }

    public User createTestUser()
    {
        User user = new User();
        user.setPassword("Password");
        user.setUsername("Derik12");
        user.setPersonName("Derik Shoen");
        user.setRole("Parent");
        return user;
    }
}
