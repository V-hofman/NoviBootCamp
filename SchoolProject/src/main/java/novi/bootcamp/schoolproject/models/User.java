package novi.bootcamp.schoolproject.models;

import javax.persistence.*;



@Entity
@Table(name = "users")
public class User {

    //region Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true,name="userID")
    private int UserID;

    @Column(nullable = false, unique = true, length = 45, name="username")
    private String Username;

    @Column(nullable = false, length = 64, name="password")
    private String Password;

    @Column(length = 45, name="personName")
    private String PersonName;

    @Column(nullable = false, name="role")
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Parent parent;

    //endregion

    //region Get/set
    public int getId() {
        return UserID;
    }

    public void setId(int id) {
        this.UserID = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        this.PersonName = personName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    //endregion

    //region Builder
    //todo create a builder template for users
    //endregion

}
