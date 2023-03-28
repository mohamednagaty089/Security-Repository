/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.global.security.JWT.controllers.secure;


import com.global.security.JWT.Entity.CustomUser;
import com.global.security.JWT.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/secure")
public class SecureController {
  
    @Autowired
    SecurityUtil securityUtil;
    
    @RequestMapping(value = "jwt/hello", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity  sayHelloUser()  {
        
        CustomUser user=securityUtil.findAuthunticatedUser();
        return ResponseEntity.ok("Hello "+user.getUsername());
        
    }


   
    @RequestMapping(value = "/hello", method = RequestMethod.GET)    
    public ResponseEntity  sayHello() throws AuthenticationException  {
        return ResponseEntity.ok("Hello secure web service");
        
    }


}
