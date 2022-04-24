package novi.bootcamp.schoolproject.models;

import javax.persistence.*;

@Entity
@Table(name = "roletype" )
public class Roles {

    //region Variables
    @Id
    @Column(name = "roleID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "roleType")
    private String name;
    //endregion

    //region Getters/setters
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
