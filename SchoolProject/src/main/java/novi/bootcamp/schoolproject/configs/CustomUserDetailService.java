package novi.bootcamp.schoolproject.configs;

import novi.bootcamp.schoolproject.models.User;
import novi.bootcamp.schoolproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    //look for the user needed for security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Search in the database
        User user = repo.findByUsername(username);

        //Can't find it then throw an error
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

}
