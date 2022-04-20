package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.Parent;
import novi.bootcamp.schoolproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

    @Query("SELECT p FROM Parent p WHERE p.user.UserID =?1")
    Parent findByUserID(int UserID);

    @Transactional
    @Modifying
    @Query("DELETE FROM Parent p WHERE p.user.UserID=?1")
    void deleteByUserID(int userId);
}
