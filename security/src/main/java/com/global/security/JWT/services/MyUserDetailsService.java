/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.global.security.JWT.services;



import com.global.security.JWT.Entity.CustomUser;
import com.global.security.JWT.Entity.Role;
import com.global.security.JWT.Entity.Users;
import com.global.security.JWT.daos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author rehab.abd-elhamid
 */
@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
	private UsersRepository userRepository;

    @Bean
    private BCryptPasswordEncoder userEncode(){
        return new BCryptPasswordEncoder();
    }
    
/*With customuser properties & actual user roles*/
    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        
      
                Users user= userRepository.findByUsername(username);
                if(user == null)
                   throw new UsernameNotFoundException("Invalid username or password.");
                else
                  return new CustomUser(user.getUserName(), user.getPassword(),user.isEnabled()==1?true:false,true,true,true, mapToGrantedAuthorities(user.getRoleList()),user.getFirstName(),user.getLastName());
                
        
        
     

    }
    
//    @Override
//    public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
//        
//      
//                Users user =userRepository.findByUsername(username);
//
//                if(user == null)
//                   throw new UsernameNotFoundException("Invalid username or password.");
//                else
//                  return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapToGrantedAuthorities(user.getRoleList()));
//                
//        
//        
//     
//
//    }
   private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleName().name()))
                .collect(Collectors.toList());
    }
    public Users save(Users users){
        users.setPassword(userEncode().encode(users.getPassword()));
        return userRepository.save(users);
    }
    public Long count(){
        return userRepository.count();
    }
    
}
