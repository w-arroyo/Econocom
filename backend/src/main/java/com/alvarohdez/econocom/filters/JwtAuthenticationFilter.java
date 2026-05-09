package com.alvarohdez.econocom.filters;

import com.alvarohdez.econocom.handlers.JwtTokenHandler;
import com.alvarohdez.econocom.security.FillerUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenHandler jwtTokenHandler;
    private final FillerUserDetailsService fillerUserDetailsService;

    public JwtAuthenticationFilter(JwtTokenHandler jwtTokenHandler, FillerUserDetailsService fillerUserDetailsService) {
        this.jwtTokenHandler = jwtTokenHandler;
        this.fillerUserDetailsService = fillerUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String extractedToken= jwtTokenHandler.getTokenFromRequest(request);
        if(extractedToken!=null && jwtTokenHandler.checkIfJwtTokenIsStillValid(extractedToken)){
            String userEmail= jwtTokenHandler.getUserEmailFromJwtToken(extractedToken);
            UserDetails userDetails=fillerUserDetailsService.loadUserByUsername(userEmail);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, // i do not want to keep any credentials after user validation
                    userDetails.getAuthorities()
            );
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // add details like IP address
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken); // adds the authenticated user to the context allowing him to access secured endpoints
            // this last one basically allows the request until completed beacuse it is authenticated
        }
        filterChain.doFilter(request,response);
    }
}
