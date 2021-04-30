package com.example.taskservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/task/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/task/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/task").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/task/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/task/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/task/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //ok for demo
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }

}
