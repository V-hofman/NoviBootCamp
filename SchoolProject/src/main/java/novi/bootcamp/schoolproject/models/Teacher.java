package novi.bootcamp.schoolproject.models;


import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {

    //region Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true,name = "TeachID")
    private int teachID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;
    //endregion

    //region Constructors
    public Teacher()
    {

    }

    public Teacher(TeacherBuilder builder)
    {
        this.teachID = builder.teachID;
        this.user = builder.user;
    }
    //endregion

    //region Getters/Setters

    public int getTeachID() {
        return teachID;
    }

    public void setTeachID(int teachID) {
        this.teachID = teachID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //endregion

    //region builder
    public static class TeacherBuilder
    {
        private int teachID;
        private User user;

        public TeacherBuilder()
        {

        }

        public TeacherBuilder(int teachID, User user)
        {
            this.teachID = teachID;
            this.user = user;
        }

        public TeacherBuilder teachID(int teachID)
        {
            this.teachID = teachID;
            return this;
        }

        public TeacherBuilder user(User user)
        {
            this.user = user;
            return this;
        }

        public Teacher build()
        {
            return  new Teacher(this);
        }

    }

    //endregion
}
