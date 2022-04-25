package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    //Query to find by userID
    @Query("SELECT t FROM Teacher t WHERE t.user.UserID =?1")
    Teacher findByUserID(int UserID);

    //Query to delete Students by userID
    @Transactional
    @Modifying
    @Query("DELETE FROM Teacher t WHERE t.user.UserID=?1")
    void deleteByUserID(int userId);
}
