package com.example.onboard.helpers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserInfo {

    private final JwtUtils jwtUtils;

    public String getEmail(HttpServletRequest request){

        final String authHeader = request.getHeader(AUTHORIZATION);
        final String userEmail;
        final String jwtToken;

        jwtToken = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(jwtToken);
        System.out.println(authHeader);
        System.out.println(userEmail);
        System.out.println(jwtToken);
        if(userEmail != null){
            return userEmail;
        }
        return null;
    }

}
