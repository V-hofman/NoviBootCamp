package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

    //region single entry manipulation

    //Query to find a parent from the parents table by userID
    @Query("SELECT p FROM Parent p WHERE p.user.UserID =?1")
    Parent findByUserID(int UserID);

    //Here we delete a parent by userID
    @Transactional
    @Modifying
    @Query("DELETE FROM Parent p WHERE p.user.UserID=?1")
    void deleteByUserID(int userId);

    @Query("SELECT p FROM Parent p WHERE p.user.PersonName =?1")
    Parent findByName(String name);

    //endregion
}
