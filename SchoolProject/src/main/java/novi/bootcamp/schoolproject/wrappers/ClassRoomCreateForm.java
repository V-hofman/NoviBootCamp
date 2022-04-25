package novi.bootcamp.schoolproject.wrappers;

import com.sun.istack.NotNull;
import novi.bootcamp.schoolproject.models.Classrooms;
import novi.bootcamp.schoolproject.models.Teacher;
import novi.bootcamp.schoolproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
public class ClassRoomCreateForm {

    @Autowired
    private UserService userService;

    @NotNull
    private String classroomNr;
    @NotNull
    private String classroomName;
    @NotNull
    private String teacherName;

    private Classrooms room = new Classrooms();
    private Teacher teacher;

    public Classrooms createClassRoom(ClassRoomCreateForm form)
    {
        room.setClassName(form.classroomName);
        room.setClassroomNR(form.classroomNr);
        this.teacher = userService.findTeacherByName(form.teacherName);
        System.out.println(userService.findTeacherByName(form.teacherName).getUser().getPersonName());
        room.setTeacher(teacher);
        return this.room;
    }

    //region Getters/setters

    public String getClassroomNr() {
        return classroomNr;
    }

    public void setClassroomNr(String classroomNr) {
        this.classroomNr = classroomNr;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Classrooms getRoom() {
        return room;
    }

    public void setRoom(Classrooms room) {
        this.room = room;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    //endregion
}
