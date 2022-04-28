package novi.bootcamp.schoolproject.services;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.Student;
import novi.bootcamp.schoolproject.repository.ClassroomRespitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ClassroomService {

    @Autowired
    ClassroomRespitory classRepo;

    public List<Classrooms> findAllRooms()
    {
        return (List<Classrooms>) classRepo.findAll();
    }

    public void saveClassroom(Classrooms room)
    {
        classRepo.save(room);
    }

    public void removeClassroomByID(int ID)
    {
        classRepo.deleteClassroomsByClassID(ID);
    }

    public void addStudent(Set<Student> student, Classrooms room)
    {
        room.setStudents(student);
        classRepo.save(room);
    }
}
