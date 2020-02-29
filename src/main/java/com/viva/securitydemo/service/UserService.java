/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viva.securitydemo.service;

import org.springframework.stereotype.Service;

import com.viva.securitydemo.model.User;

/**
 *
 * @author haardik.sikka
 */

@Service
public class UserService {
    
    public boolean authenticateUser(User user){
        if(user.getUsername().equals("Haardik") && user.getPassword().equals("password")){
            return true;
        }
        return false;
    }
    
    public User getUserDetails(String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        return user;
    }
            
}
