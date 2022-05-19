package novi.bootcamp.schoolproject.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


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

    @Column(name = "userPic")
    private String picture;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "roleID")
            )
    private Set<Roles> roles = new HashSet<>();
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Transient
    public String getImagePath()
    {
        if(this.picture == null)
        {
            return null;
        }
        return "user-photos/" + getId() + "/" + this.picture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public void addRole(String role)
    {
        Roles tempRole = new Roles();
        switch(role)
        {
            case "STUDENT":
                tempRole.setId(1);
                tempRole.setName(role);
                break;
            case "ADMIN":
                tempRole.setId(4);
                tempRole.setName(role);
                break;
            default:
                throw new RuntimeException("Role not found!");
        }
        this.roles.add(tempRole);

    }

    //endregion

}
