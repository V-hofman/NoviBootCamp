package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.Classrooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRespitory extends JpaRepository<Classrooms, Integer> {
}
