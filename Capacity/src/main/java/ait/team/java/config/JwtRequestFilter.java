package ait.team.java.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ait.team.java.service.impl.UserService;
import io.jsonwebtoken.ExpiredJwtException;

/*
The JwtRequestFilter extends the Spring Web Filter OncePerRequestFilter class. For any incoming request this Filter
class gets executed. It checks if the request has a valid JWT token. If it has a valid JWT Token then it sets the
 Authentication in the context, to specify that the current user is authenticated.
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

       // final String requestTokenHeader = request.getHeader("Authorization");

        Cookie[] cookies = request.getCookies();
        
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
/*    	  if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
              jwtToken = requestTokenHeader.substring(7);
              try {
                  username = jwtTokenUtil.getUsernameFromToken(jwtToken);
              } catch (IllegalArgumentException e) {
                  System.out.println("Unable to get JWT Token");
              } catch (ExpiredJwtException e) {
                  System.out.println("JWT Token has expired");
              }
          } else {
              logger.warn("JWT Token does not begin with Bearer String");
          }
        */
        if (cookies != null) {
       	 for (Cookie cookie : cookies) {
       	   if (cookie.getName().equals("token")) {
       		jwtToken = cookie.getValue();
	       		try {
	                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
	            } catch (IllegalArgumentException e) {
	                System.out.println("Unable to get JWT Token");
	            } catch (ExpiredJwtException e) {
	                System.out.println("JWT Token has expired");
	                deleteCookie("token","", response);
	    	        deleteCookie("userName","", response);
	    	        deleteCookie("role","", response);
	    	        deleteCookie("studentCode", "", response);
	    	        deleteCookie("imageUser", "", response);
	            }
       	    }
       	  }
       	}else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
	    private void deleteCookie(String name, String value, HttpServletResponse response) {
			Cookie cookie = new Cookie(name, value);
	        cookie.setMaxAge(0); 
	        cookie.setPath("/");
	        response.addCookie(cookie);
		}
}