/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.global.security.JWT.controllers.unsecure;


import com.global.security.JWT.Entity.CustomUser;
import com.global.security.JWT.dtos.UserLoginDto;
import com.global.security.JWT.dtos.UserLoginResponse;
import com.global.security.JWT.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */

@RestController
@RequestMapping("${allowed.start}/user")
public class LoginController {
  
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

   
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity  login(@RequestBody UserLoginDto userObj)  {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userObj.getUserName(),
                        userObj.getPlainPassword()
                )
        );//for check authenticated user if authenticated will generated jwt token
       
        CustomUser userDetails = (CustomUser) userDetailsService.loadUserByUsername(userObj.getUserName());
       final String fullName=jwtTokenUtil.generateStringToken(userDetails.getFirstName()+userDetails.getLastName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new UserLoginResponse(token,fullName));
       
    }



}
