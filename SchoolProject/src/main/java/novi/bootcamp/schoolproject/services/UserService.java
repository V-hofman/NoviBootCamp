package novi.bootcamp.schoolproject.services;

import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static novi.bootcamp.schoolproject.login.PasswordEncoder.EncryptPassword;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;


    public void deleteUserByUsername(User user)
    {
        userRepo.deleteByUsername(user.getUsername());
    }

    public void saveUser(User user)
    {
        user.setPassword(EncryptPassword(user.getPassword()));
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
                                newUser.setPassword(EncryptPassword(user.getPassword()));
                                newUser.setUsername(user.getUsername());
                                newUser.setPersonName(user.getPersonName());
                                newUser.setRole(user.getRole());
                                newUser.setId(replaceId);
                                return userRepo.save(newUser);
                            }
                    ).orElse(
                            null
                    );

        }catch (Exception e)
        {
            userRepo.save(user);
        }
    }
}
