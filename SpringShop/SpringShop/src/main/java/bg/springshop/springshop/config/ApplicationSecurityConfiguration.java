package bg.springshop.springshop.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ApplicationSecurityConfiguration  extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder,
                                            UserDetailsService userDetailsService) {

        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests().
            // with this line we allow access to all static resources
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
            // the next line allows access to the home page, login page and registration for everyone
                antMatchers("/", "/users/login", "/users/register", "/category/{name}", "/products/all").permitAll().
                antMatchers("/**").authenticated()
            .and()
            .formLogin()
            .loginPage("/users/login").usernameParameter("username")
            .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
            .defaultSuccessUrl("/")
            .failureForwardUrl("/users/error")
            .and()
            .logout().clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
            .logoutSuccessUrl("/users/login")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.
            userDetailsService(userDetailsService).
            passwordEncoder(passwordEncoder);
    }
}
