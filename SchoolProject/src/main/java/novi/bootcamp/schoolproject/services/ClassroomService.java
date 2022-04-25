package novi.bootcamp.schoolproject.services;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.repository.ClassroomRespitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    ClassroomRespitory classRepo;

    public List<Classrooms> findAllRooms()
    {
        return (List<Classrooms>) classRepo.findAll();
    }
}
