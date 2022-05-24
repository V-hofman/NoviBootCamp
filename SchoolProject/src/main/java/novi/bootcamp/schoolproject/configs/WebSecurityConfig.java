package novi.bootcamp.schoolproject.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new CustomUserDetailService();
    }



    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.inMemoryAuthentication().withUser("user1").password("user1Pass").roles("ADMIN").authorities("ADMIN");

    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {

    }
    //Here we determine who gets to go on each page

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/font/**", "/imgs/**")
                .permitAll() //Allow everyone to see /css/ /font/ and /imgs/
                .and()
                .authorizeRequests()
                .antMatchers("/Admin/**").hasAuthority("ADMIN") //Only Admin can see /Admin/**
                .antMatchers("/Student/**").hasAnyAuthority("STUDENT", "ADMIN") //Student and Admin can see /Student
                .anyRequest()
                .authenticated()
                .and()    //START Custom login define
                .formLogin() //Define that we want a custom form login page using HTML
                .loginPage("/login") //On which endpoint do we have the Login form
                .usernameParameter("Username") //Username for the login is the Username ID in the HTML
                .passwordParameter("Password") //Password for the login is the Password ID in the HTML
                .defaultSuccessUrl("/Redirect", true) //If successful login go to /Redirect
                .permitAll() //Everyone can access /Login END Custom login define
                .and()
                .logout() //START Custom logout
                .invalidateHttpSession(true) //Remove the HttpSession
                .deleteCookies("JSESSIONID") //Delete the cookie that keeps you logged in
                .logoutSuccessUrl("/login") //If you log out, go to /login
                .permitAll() //Everyone can log out
                ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        //Setup some configs
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    //Here we say how to search for the info we need for the login details
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception
    {
        //Set the authentication with a passwordEncoder
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password enabled from users where username=?") //Grab the needed stuff from the users table
                .authoritiesByUsernameQuery("select username, role from users where username=?") //Grab the roles for the user from the users table
                ;
    }


}
