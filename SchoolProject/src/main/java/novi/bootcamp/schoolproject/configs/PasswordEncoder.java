package novi.bootcamp.schoolproject.configs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //make sure to encode the password
    public static String EncryptPassword(String password)
    {
        return encoder.encode(password);
    }
}
