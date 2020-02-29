/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viva.securitydemo.controller;

import com.viva.securitydemo.model.User;
import com.viva.securitydemo.service.UserService;
import com.viva.securitydemo.tokenconfig.MyTokenUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author haardik.sikka
 */

@RestController
public class TokenController {
   
    @Autowired
    UserService userService;
    
    @Autowired
    MyTokenUtility tokenUtil;
    
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public String authenticate(@RequestBody User user ){
        System.out.println("-------inside authentication for use "+ user.getUsername());
        
        if(userService.authenticateUser(user)){
            String token = tokenUtil.createToken(user);
            System.out.println("--------Token "+ token);
            return token;
        }
        
        return "Wrong Username Password";
    }
    
}
