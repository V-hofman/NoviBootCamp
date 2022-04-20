package novi.bootcamp.schoolproject.models;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    //region Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true,name = "studentID")
    private int studentID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    //endregion

    //region Constructors
    public Student()
    {

    }

    public Student(studentBuilder builder) {
        this.studentID = studentID;
        this.user = user;
    }

    //endregion

    //region Getters/setters
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //endregion

    //region Builder
    public static class studentBuilder
    {
        private int parentID;
        private User user;

        public studentBuilder()
        {

        }

        public studentBuilder(int parentID, User user)
        {
            this.user = user;
            this.parentID = parentID;
        }

        public studentBuilder parentID(int parentID)
        {
            this.parentID = parentID;
            return this;
        }

        public studentBuilder user(User user)
        {
            this.user = user;
            return this;
        }

        public Student build()
        {
            return new Student(this);
        }
    }
    //endregion
}