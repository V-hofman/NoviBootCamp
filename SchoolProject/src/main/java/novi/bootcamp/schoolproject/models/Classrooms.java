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
        this.ClassID = builder.classID;
    }

    //region Variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true,name = "ClassID")
    private int ClassID;

    @Column(name = "ClassName", length = 45)
    private String className;

    @Column(name = "ClassRoomNR")
    private String classroomNR;

    @Column(name = "ClassDate", length = 45)
    private String classDate;

    @Column(name = "ClassTeacher", length = 45)
    private String classTeacher;
    //endregion

    //region Getters/setters

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
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

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    //endregion


    //region Builder
    public static class roomBuilder
    {
        private int classID;
        private String className;
        private String classRoom;
        private String classTeacher;
        private String classDate;

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


        public roomBuilder classTeacher(String classTeacher)
        {
            this.classTeacher = classTeacher;
            return this;
        }

        public roomBuilder classDate(String classDate)
        {
            this.classDate = classDate;
            return this;
        }

        public Classrooms build()
        {
            return new Classrooms(this);
        }
    }

    //endregion
}
