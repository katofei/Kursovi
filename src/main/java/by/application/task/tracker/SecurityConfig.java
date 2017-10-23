package by.application.task.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .rolePrefix("ROLE_")
				.usersByUsernameQuery(
                "select login as username,password, enabled from users where login=?")
				.authoritiesByUsernameQuery(
                "select u.login as username, r.role_name as role from roles r,users u "
                        + "where u.user_role_role_id = r.role_id and u.login=?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/authorization").permitAll()
                .antMatchers("/starter").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .and()
            .csrf().disable()
            .formLogin()
                .loginPage("/authorization")
                .defaultSuccessUrl("/starter")
                .and()
            .logout()
                .logoutUrl("/authorization")
                .logoutSuccessUrl("/");
                /*.and()
            .exceptionHandling()
                .accessDeniedPage("/access-denied");*/

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
