package novi.bootcamp.schoolproject.models;

import javax.persistence.*;

@Entity
@Table(name = "parents")
public class Parent {

    //region Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true,name = "ParentID")
    private int ParentID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    //endregion

    //region constructors
    public Parent()
    {

    }

    public Parent(Builder builder) {
        this.ParentID = builder.ParentID;
        this.user = builder.user;
    }

    //endregion

    //region getter/setter
    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int parentID) {
        ParentID = parentID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//endregion

    //region Builder
    public static class Builder
    {
        private  int ParentID;
        private  User user;

        public Builder()
        {

        }

        public Builder(int ParentID, User user)
        {
            this.ParentID = ParentID;
            this.user = user;
        }

        public Builder ParentID(int ParentID)
        {
            this.ParentID = ParentID;
            return this;
        }

        public Builder User(User user)
        {
            this.user = user;
            return this;
        }

        public Parent build()
        {
            return new Parent(this);
        }

    }

    //endregion

}
