package novi.bootcamp.schoolproject.services;

import novi.bootcamp.schoolproject.models.Parent;
import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.repository.ParentRepository;
import novi.bootcamp.schoolproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static novi.bootcamp.schoolproject.login.PasswordEncoder.EncryptPassword;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ParentRepository parentRepo;


    public void deleteUserByUsername(User user)
    {
        User tempUser = userRepo.findByUsername(user.getUsername());
        if(tempUser.getRole().equalsIgnoreCase("Parent"))
        {
            parentRepo.deleteByUserID(tempUser.getId());
        }
        userRepo.deleteByUsername(user.getUsername());
    }

    public void saveUser(User user)
    {
        user.setPassword(EncryptPassword(user.getPassword()));
        if(user.getRole().equalsIgnoreCase("parent"))
        {
            saveParent(user);
        }
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

    public void saveParent(User user)
    {
        if(parentRepo.findByUserID(user.getId()) == null)
        {
            Parent tempParent = new Parent.Builder()
                    .User(user)
                    .build();
            parentRepo.save(tempParent);
        }
    }
}
