package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.Roles;
import novi.bootcamp.schoolproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //region single entry manipulation

    //Query to find users by username
    @Query("SELECT u FROM User u WHERE u.Username =?1")
    User findByUsername(String username);

    //Query to delete users by username
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.Username=?1")
    void deleteByUsername(String username);
    //endregion



}
