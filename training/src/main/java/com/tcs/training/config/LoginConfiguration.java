package com.tcs.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class LoginConfiguration extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll()
        .antMatchers("/home.jsp").hasAnyRole("ADMIN")
        .anyRequest()
        .authenticated().and().formLogin().defaultSuccessUrl("/home.jsp")
        .and().logout().permitAll();
        
        
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authMnger) throws Exception{
        authMnger.inMemoryAuthentication().withUser("admin")
        .password("{noop}admin").roles("ADMIN");
    }
}
