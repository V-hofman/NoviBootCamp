package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.Classrooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassroomRespitory extends JpaRepository<Classrooms, Integer> {

    @Query("SELECT c FROM Classrooms c WHERE c.className =?1")
    Classrooms findClassroomsByClassName(String name);
}
