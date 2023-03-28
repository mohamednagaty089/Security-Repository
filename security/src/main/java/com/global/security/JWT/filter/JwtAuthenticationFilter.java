package com.global.security.JWT.filter;



import com.global.security.JWT.constants.ConstantStrings;
import com.global.security.JWT.services.MyUserDetailsService;
import com.global.security.JWT.utils.FilterExceptionsUtil;
import com.global.security.JWT.utils.JwtTokenUtil;
import com.global.security.JWT.utils.SecurityUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter   {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    SecurityUtil securityUtil;
     protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,FilterChain chain) throws IOException, ServletException {
         

      
        final String authHeader = req.getHeader(ConstantStrings.HEADER_STRING);
        String username = null;
        String authToken = null;    
        if ("OPTIONS".equals(req.getMethod())) {
            //dont change this lines to alow get request
            res.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);

        } else {
           try{
                if (authHeader != null && authHeader.startsWith(ConstantStrings.TOKEN_PREFIX)) 
                {
                                  
                     authToken = authHeader.replace(ConstantStrings.TOKEN_PREFIX,"");                   
                     username = jwtTokenUtil.getUsernameFromToken(authToken);
                } 
                      // If request do have a username, and there is no spring security context asociated.
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
                    {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        if (jwtTokenUtil.validateToken(authToken, userDetails)) 
                        {
                            //securityUtil.setAuthentication(username);
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                            logger.info("authenticated user " + username + ", setting security context");
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                    chain.doFilter(req, res);
           }
           catch(ExpiredJwtException ex)
           {
                logger.error(ex.getMessage());
                FilterExceptionsUtil.constructResponse(res, HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
           }
                



        }

    }

    
}
