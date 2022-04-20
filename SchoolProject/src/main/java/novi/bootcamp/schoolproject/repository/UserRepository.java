package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.Username =?1")
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.Username=?1")
    void deleteByUsername(String username);


}
