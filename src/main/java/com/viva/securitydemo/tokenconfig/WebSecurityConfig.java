/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viva.securitydemo.tokenconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author haardik.sikka
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
        authorizeRequests().antMatchers("/login").permitAll().                   
        anyRequest().authenticated().and().httpBasic();        
     
        http.addFilterBefore(new RequestFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    
    @Override
    public void configure(WebSecurity web) {
    	web.ignoring().antMatchers("/login");
    }
}
