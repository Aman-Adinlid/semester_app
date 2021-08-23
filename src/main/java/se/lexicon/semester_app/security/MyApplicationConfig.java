package se.lexicon.semester_app.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se.lexicon.semester_app.entity.UserType;
import se.lexicon.semester_app.service.UserService;

import static se.lexicon.semester_app.entity.UserType.*;


@Configuration
@EnableWebSecurity
public class MyApplicationConfig extends WebSecurityConfigurerAdapter {

    UserService userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/",
                             "index",
                             "/api/v1/confirm",
                             "/api/v1/register",
                             "/api/v1/register/",
                             "/api/v1/company",
                             "/api/v1/company/public",
                             "/api/v1/employee/company/public",
                             "/api/v1/vacationDay/company/public",
                             "/api/v1/user/public",
                             "/api/v1/employee/public").permitAll()
                .antMatchers("/admin/api/**").hasAnyRole(ADMIN.name(),SUPERVISOR.name())
                .antMatchers("/owner/api/**").hasRole(SUPERVISOR.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and().logout().logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID","remember-me")
                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }
}
