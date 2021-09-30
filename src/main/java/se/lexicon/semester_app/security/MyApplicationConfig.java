package se.lexicon.semester_app.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static se.lexicon.semester_app.entity.UserType.ADMIN;
import static se.lexicon.semester_app.entity.UserType.SUPERVISOR;
import se.lexicon.semester_app.jwtAuthorization.filters.JwtAuthorizationFilter;
import se.lexicon.semester_app.service.UserService;

@Configuration
@EnableWebSecurity
public class MyApplicationConfig {

    @Order(1)
    @Configuration
    @RequiredArgsConstructor
    public static class MobileAppSecurityConfig extends WebSecurityConfigurerAdapter {

        private final UserService userService;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .antMatcher("/api/v1/mobile/**")
                .authorizeRequests()
                .antMatchers("/api/v1/mobile/authenticate",
                             "/api/v1/mobile/resetPassword",
                             "/api/v1/mobile/refreshToken/**").permitAll()
                .antMatchers("/api/v1/mobile/getAllEmployeeData",
                             "/api/v1/mobile/addNewEmployee").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Order(2)
    @Configuration
    public static class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
        protected UserService userService;
        protected BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        public void setUserService(UserService userService) {
            this.userService = userService;
        }

        @Autowired
        public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
            this.bCryptPasswordEncoder = passwordEncoder;
        }

        @Bean
        public DaoAuthenticationProvider webAuthenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(userService);
            provider.setPasswordEncoder(bCryptPasswordEncoder);
            return provider;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(webAuthenticationProvider());
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
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
                             "/api/v1/employee/company/public",
                             "/api/v1/vacationDay/company/public",
                             "/api/v1/vacationDay/public",
                             "/api/v1/user/public").permitAll()
                .antMatchers("/admin/api/**").hasAnyRole(ADMIN.name(), SUPERVISOR.name())
                .antMatchers("/owner/api/**").hasRole(SUPERVISOR.name())
                .anyRequest().authenticated()
                .and().formLogin()
                .and().logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");
        }
    }
}

