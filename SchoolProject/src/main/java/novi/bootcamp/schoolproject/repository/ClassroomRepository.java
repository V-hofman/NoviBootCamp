package novi.bootcamp.schoolproject.repository;

import novi.bootcamp.schoolproject.models.Classrooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ClassroomRepository extends JpaRepository<Classrooms, Integer> {

    //Delete a room by classID
    @Transactional
    @Modifying
    @Query("DELETE FROM Classrooms c WHERE c.ClassID =?1")
    void deleteClassroomsByClassID(int ID);

}
