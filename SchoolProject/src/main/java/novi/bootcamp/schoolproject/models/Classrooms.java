package novi.bootcamp.schoolproject.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classroom")
public class Classrooms {

    public Classrooms()
    {

    }

    public Classrooms(roomBuilder builder)
    {
        this.className = builder.className;
        this.classroomNR = builder.classRoom;
        this.students = builder.students;
        this.ClassID = builder.classID;
        this.teacher = builder.teacher;
    }

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

    //region Functions

    public void addStudent(Student student)
    {
        this.students.add(student);
    }
    //endregion

    //region Builder
    public static class roomBuilder
    {
        private int classID;
        private String className;
        private String classRoom;
        private Teacher teacher;
        private Set<Student> students = new HashSet<>();

        public roomBuilder()
        {

        }

        public roomBuilder classID(int classID)
        {
            this.classID = classID;
            return this;
        }

        public roomBuilder className(String className)
        {
            this.className = className;
            return this;
        }

        public roomBuilder classRoom(String classRoom)
        {
            this.classRoom = classRoom;
            return this;
        }

        public roomBuilder students(Set<Student> students)
        {
            this.students = students;
            return this;
        }

        public Classrooms build()
        {
            return new Classrooms(this);
        }
    }

    //endregion
}
