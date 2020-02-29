/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viva.securitydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author haardik.sikka
 */

@RestController
public class UserController {
    
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/helloadmin")
    public String helloAdmin(){           
        return "hello admin";
    }
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping("/hellouser")
    public String helloUser(){            
        return "hello user";
    }
	
}
