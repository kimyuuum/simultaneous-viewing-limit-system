package com.skb.pushservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Enable authentication with declared user : UserA, UserB
     *
     * Spring Security provides default login form where insert userName and password.
     */

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        auth.inMemoryAuthentication()
                            .withUser("UserA").password("{noop}UserA").roles("USER")
                            .and()
                            .withUser("UserB").password("{noop}UserB").roles("USER");

    }

    /**
     * Disable CSRF to simplify this demo and use default login form.
     */

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .and()
                    .httpBasic();
    }

}
