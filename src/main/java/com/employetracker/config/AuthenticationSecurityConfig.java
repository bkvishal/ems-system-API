package com.employetracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author : Vishal Srivastava
 * @Date : 24-03-2021
 **/

@Configuration
@EnableWebSecurity
public class AuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    // Approach 1 for setting basic auth with define user and password using AuthManagerBuilder --

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("shiva")
                .password(passwordEncoder.encode("baba"))
                .authorities("ADMIN");
    }*/

    // Approach 2 for setting basic auth with define user and password using UserDetailService --

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userFirst = User.builder()
                .username("vishal")
                .password(passwordEncoder.encode("password"))
                .roles("EMPLOYEE")  // ROLE_EMPLOYEE
                .build();

        UserDetails adminUser = User.builder()
                .username("Shiva")
                .password(passwordEncoder.encode("god"))
                .roles("ADMIN")  // ROLE_ADMIN
                .build();

        return new InMemoryUserDetailsManager(
                userFirst,
                adminUser
        );
    }

}
