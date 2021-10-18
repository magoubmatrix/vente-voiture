package com.app.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.security.jwt.JwtProvider;


@Service

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtProvider jwtProvider; 
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwtFromRequest = getJwtFromRequest(request);
	
        if (StringUtils.hasText(jwtFromRequest)&& SecurityContextHolder.getContext().getAuthentication() == null  ) {
            String username = jwtProvider.extractUsername(jwtFromRequest);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            
        }
        
        filterChain.doFilter(request, response);
	}

	
	private String getJwtFromRequest(HttpServletRequest request) {
       
		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken)&& bearerToken.startsWith("Bearer ") ) {
			return bearerToken.substring(7);
		}
		return bearerToken;
	}

}
