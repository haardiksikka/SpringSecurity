/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viva.securitydemo.tokenconfig;

import java.util.Base64;
import org.springframework.stereotype.Component;

import com.viva.securitydemo.model.User;

/**
 *
 * @author haardik.sikka
 */

@Component
public class MyTokenUtility {
    public String createToken(User user) {
		return Base64.getEncoder().encodeToString(new String(user.getUsername()+"."+user.getPassword()).getBytes()); 
	}
	
	public boolean validateToken(String token, User user) {
		//decode token
		
		Base64.Decoder decoder = Base64.getDecoder();  
        String decodedToken = new String(decoder.decode(token));  
		
        String[] t = decodedToken.split("\\.");
        String userName = t[0];
        System.out.println(userName+"."+user.getUsername());
        if(userName.equals(user.getUsername())) {
        	return true;
        }
        //validate username
        
		return false;
	}
	
	public String getUserNameFromToken(String token) {
            System.out.println("enetering");
		Base64.Decoder decoder = Base64.getDecoder();  
        String decodedToken = new String(decoder.decode(token));  
	System.out.println(decodedToken);	
        String[] t = decodedToken.split("\\.");
        String userName = t[0];
        return userName;
	}
}
