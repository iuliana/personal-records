package com.pr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by iuliana.cosmina on 9/14/15.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                    .withUser("john").password("doe").roles("USER").and()
                    .withUser("jane").password("doe").roles("USER,ADMIN").and()
                    .withUser("admin").password("admin").roles("ADMIN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/resources/*","/images/*","/styles/*","/auth*").permitAll()
            .antMatchers("/persons/newPerson").hasRole("ADMIN")
            .antMatchers("/**").hasAnyRole("USER, ADMIN")
            .anyRequest().authenticated()
            .and()
        .formLogin()
                .loginPage("/auth")
                .failureUrl("/auth?auth_error=1")
                .defaultSuccessUrl("/")
            .and()
        .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/");

    }

}
