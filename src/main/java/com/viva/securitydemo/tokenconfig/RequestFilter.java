/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viva.securitydemo.tokenconfig;

import com.viva.securitydemo.model.User;
import com.viva.securitydemo.service.UserService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;


/**
 *
 * @author haardik.sikka
 */
public class RequestFilter implements Filter {

//    @Autowired
//    private UserService userService;

//    @Autowired
//    private MyTokenUtility tokenUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		MyTokenUtility tokenUtil = new MyTokenUtility();
		UserService userService = new UserService();
		HttpServletRequest req = (HttpServletRequest) request;
		final String requestTokenHeader = req.getHeader("Authorization");
		String username = null;
		String token = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			token = requestTokenHeader.substring(7);
			System.out.println(token);
			try {

				username = tokenUtil.getUserNameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get Token");
			} catch (Exception e) {
				System.out.println(e + " " + e.getMessage() + " " + e.getStackTrace());
			}
		} else {

		}

		// Once we get the token validate it.
		if (username != null) {

			User userDetails = userService.getUserDetails(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (tokenUtil.validateToken(token, userDetails)) {
				ArrayList<GrantedAuthority> list = new ArrayList<>();

				// add userdetails object in user service
				list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, list);

				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				System.out.println(usernamePasswordAuthenticationToken.toString());
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			}
		}

		chain.doFilter(request, response);
	}
}
