package novi.bootcamp.schoolproject.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static void main(String[] args)
    {
        String rawPassword = "student";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }

    public static String EncryptPassword(String password)
    {
        return encoder.encode(password);
    }
}
