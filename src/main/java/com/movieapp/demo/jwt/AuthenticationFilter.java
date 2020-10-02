package com.movieapp.demo.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.demo.models.LoginModel;
import io.jsonwebtoken.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.auth0.jwt.JWT;

// this class makes use of valid username  and password together with secret key to generate it own web token.
// this class UsernamePasswordAuthenticationFilter it has the attemptAuthentication successfulAuthentication
// valdate
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    //
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response){
        LoginModel credentials = null;
        try{
            credentials = new ObjectMapper().readValue(request.getInputStream(),LoginModel.class);
        } catch (IOException e){
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword(), new ArrayList<>()
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return authentication;
    }

    public String GenerateToken(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("Username: " + userDetails.getUsername());
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
        return token;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = GenerateToken(authResult);
            response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
    }
}
