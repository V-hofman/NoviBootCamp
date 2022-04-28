package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ClassroomRespitory extends JpaRepository<Classrooms, Integer> {

    @Query("SELECT c FROM Classrooms c WHERE c.className =?1")
    Classrooms findClassroomsByClassName(String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM Classrooms c WHERE c.ClassID =?1")
    void deleteClassroomsByClassID(int ID);

}
