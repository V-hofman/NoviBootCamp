package novi.bootcamp.schoolproject.services;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classRepo;

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

}
