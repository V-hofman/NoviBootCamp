package novi.bootcamp.schoolproject.services;

import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classRepo;

    //Lists every classroom in the database
    public List<Classrooms> findAllRooms()
    {
        return (List<Classrooms>) classRepo.findAll();
    }

    //Saves a classroom inside the database
    public void saveClassroom(Classrooms room) throws ParseException {
        //Create a string from the date anf format him to the proper date to display
        String oldString = room.getClassDate();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldString);
        String newString = new SimpleDateFormat("dd-MM-yyyy").format(date);

        //Set the date to the proper string
        room.setClassDate(newString);

        //Save the classroom to the database
        classRepo.save(room);
    }

    //Removes a classroom from the database using an ID
    public void removeClassroomByID(int ID)
    {
        //Use the ID to delete a classroom
        classRepo.deleteClassroomsByClassID(ID);
    }

}
