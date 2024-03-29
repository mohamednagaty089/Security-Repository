/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.global.security.JWT.config;




import com.global.security.JWT.filter.Exception.JwtAuthenticationEntryPoint;
import com.global.security.JWT.filter.JwtAuthenticationFilter;
import com.global.security.JWT.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author rehab.abd-elhamid
 */
//https://github.com/szerhusenBC/jwt-spring-security-demo/blob/master/src/main/java/org/zerhusen/config/WebSecurityConfig.java
//https://gitlab.com/palmapps/jwt-spring-security-demo/blob/7a05e9cd77af737e5b51197f8fdf88f20d022d29/src/main/java/nl/palmapps/myawesomeproject/security/model/AuthenticatedUser.java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    
    private final String[] byPassedUrls= {
                         "/mail/**","/login"
    };
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
        
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    //as AuthenticationManager not a bean so we must register it as a bean  before use
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//        @Bean
//	public JwtAuthenticationFilter authenticationJwtTokenFilter() {
//		return new JwtAuthenticationFilter();
//	}
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        
        httpSecurity.csrf().disable()
		    .authorizeRequests().antMatchers("/api/v1/user/login").permitAll()//BYPASS authenticate end point
		    .anyRequest().authenticated().and()
		    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                    .and().sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//as no sessions on spring boot
	httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // tell security to use jwt filter
                
    }
// @Override
//    public void configure(WebSecurity web) throws Exception {
//        // jwtFilter will ignore the below paths
//        web
//                .ignoring()
//                .antMatchers(
//                   
//                        "/authenticate"
//                );
//                }
////    

}
