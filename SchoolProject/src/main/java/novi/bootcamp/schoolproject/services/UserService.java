package novi.bootcamp.schoolproject.services;

import novi.bootcamp.schoolproject.models.*;
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



    public void deleteUserByUsername(User user)
    {
        User tempUser = userRepo.findByUsername(user.getUsername());
        switch(tempUser.getRole().toLowerCase(Locale.ROOT))
        {
            case "admin":
                break;
            case "student":
                studentRepo.deleteByUserID(tempUser.getId());
                break;
            default:
                throw new RuntimeException("No role for user was found!");
        }
        userRepo.deleteByUsername(user.getUsername());
    }

    public User getUserByUserName(String username)
    {
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user)
    {
        user.setPassword(EncryptPassword(user.getPassword()));
        switch(user.getRole().toLowerCase(Locale.ROOT))
        {
            case "student":
                saveStudent(user);
                break;
            case "admin":
                break;
            default:
                throw new RuntimeException("No role for user was found!");
        }
        if(user.getRoles().isEmpty())
        {
            user.addRole(user.getRole().toUpperCase(Locale.ROOT));
        }
        userRepo.save(user);
    }
    public void updateImage(User user)
    {
        userRepo.save(user);
    }

    public List<User> findAllUsers()
    {
        return (List<User>) userRepo.findAll();
    }

    public void updateUser(User user)
    {

        try{
            int replaceId = userRepo.findByUsername(user.getUsername()).getId();
            userRepo.findById(replaceId)
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
            e.printStackTrace();
            saveUser(user);
        }
    }


    //region Students

    public void saveStudent(User user)
    {
        if(studentRepo.findByUserID(user.getId()) == null)
        {
            Student tempStudent = new Student.studentBuilder()
                    .user(user)
                    .build();
            studentRepo.save(tempStudent);
        }
    }

    public Student findByUsername(String username)
    {
        return studentRepo.findByUsername(username);
    }
    //endregion

}
