package novi.bootcamp.schoolproject.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classroom")
public class Classrooms {

    //region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true,name = "ClassID")
    private int ClassID;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Teacher_TeachID", referencedColumnName = "TeachID")
    private Teacher teacher;

    @Column(name = "ClassName")
    private String className;

    @Column(name = "ClassRoomNR")
    private String classroomNR;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "classroomstudent",
            joinColumns = @JoinColumn(name = "classroom_classroomID"),
            inverseJoinColumns = @JoinColumn(name = "student_user_userID")
    )
    private Set<Student> students = new HashSet<>();
    //endregion

    //region Getters/setters

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassroomNR() {
        return classroomNR;
    }

    public void setClassroomNR(String classRoomNR) {
        this.classroomNR = classRoomNR;
    }


    public String getTeacherName()
    {
        return this.teacher.getUser().getPersonName();
    }

    //endregion
}
