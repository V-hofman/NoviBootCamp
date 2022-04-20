package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.user.UserID =?1")
    Student findByUserID(int UserID);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.user.UserID=?1")
    void deleteByUserID(int userId);
}
