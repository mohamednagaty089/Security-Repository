package com.global.security.Service;

import com.global.security.Entity.AppUser;
import com.global.security.Entity.CustomUser;
import com.global.security.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
     private UserRepo userRepo;
    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser=userRepo.findByUsername(username);
        if(appUser==null){
            throw new UsernameNotFoundException("this user not found");
        }else{
            return new CustomUser(appUser.getUsername(),appUser.getPassword(),true,true,true,true,getAuthorities(appUser),appUser.getFirstname(), appUser.getLastname());
        }



    }
    private static List<GrantedAuthority> getAuthorities(AppUser user) {

		List<GrantedAuthority> authorities = new ArrayList<>();

		 if(!user.getRoles().isEmpty()) {
		        	user.getRoles().forEach(role -> {
		        		authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		        	});
		        }
		     return authorities;
		}
}
