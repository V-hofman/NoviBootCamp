package novi.bootcamp.schoolproject.services;

import novi.bootcamp.schoolproject.models.Student;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.repository.StudentRepository;
import novi.bootcamp.schoolproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static novi.bootcamp.schoolproject.configs.PasswordEncoder.EncryptPassword;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StudentRepository studentRepo;

    //Delete a user by username including their children if present
    public void deleteUserByUsername(User user)
    {
        //Grab the user from Database
        User tempUser = userRepo.findByUsername(user.getUsername());

        //Depending on the role we can do different things
        switch(tempUser.getRole().toLowerCase(Locale.ROOT))
        {
            //Admin has no special case
            case "admin":
                break;
            //Student needs to have the child object deleted before the user can be deleted
            case "student":
                studentRepo.deleteByUserID(tempUser.getId());
                break;
            //If the role can't be found something went wrong so we throw an error
            default:
                throw new RuntimeException("No role for user was found!");
        }
        //Then we finally delete the actual user object from the database
        userRepo.deleteByUsername(user.getUsername());
    }

    //Grab a user by their username
    public User getUserByUserName(String username)
    {
        return userRepo.findByUsername(username);
    }

    //Save a user to the database as well as their children
    public void saveUser(User user)
    {
        //Check if the username is taken already
        if(userRepo.findByUsername(user.getUsername()) != null)
        {
            //If it exists throw an error
            throw new RuntimeException("User exists!");
        }

        //If the user has no authorities we give the authority that belongs with their role
        if(user.getRoles().isEmpty())
        {
            user.addRole(user.getRole().toUpperCase(Locale.ROOT));
        }

        //Make sure we encrypt the password before saving it
        user.setPassword(EncryptPassword(user.getPassword()));
        userRepo.save(user);

        //If the user has a special role we might need to do different actions
        switch(user.getRole().toLowerCase(Locale.ROOT))
        {

            //Save the child object if its a student
            case "student":
                saveStudent(user);
                break;
            //Admin has no special cases
            case "admin":
                break;
            //it really shouldn't hit this exception, but who knows, in case it got through we check again
            default:
                throw new RuntimeException("No role for user was found!");
        }
    }

    //Allows users to upload an image to their profile
    public void updateImage(User user)
    {
        userRepo.save(user);
    }

    //Find all users
    public List<User> findAllUsers()
    {
        return (List<User>) userRepo.findAll();
    }

    //Update a user
    public void updateUser(User user)
    {

        try{
            //Get the existing user
            int replaceId = userRepo.findByUsername(user.getUsername()).getId();
            userRepo.findById(replaceId)
                    //Map new values to a new user and then save it
                    .map(
                            newUser -> {
                                newUser.setPassword(user.getPassword());
                                newUser.setUsername(user.getUsername());
                                newUser.setPersonName(user.getPersonName());
                                newUser.setRole(user.getRole());
                                newUser.setId(replaceId);
                                saveUser(newUser);
                                return null;
                            }
                    ).orElse(
                            null
                    );

        }catch (Exception e)
        {
            //If it cant be found we just save it
            saveUser(user);
        }
    }


    //region Students

    //Save a student to the database
    public void saveStudent(User user)
    {
        //If the student doesn't exist in the database we create it
        if(studentRepo.findByUserID(user.getId()) == null)
        {
            Student tempStudent = new Student.studentBuilder()
                    .user(user)
                    .build();
            studentRepo.save(tempStudent);
        }
    }
    //endregion

}
