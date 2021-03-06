package org.studentnr.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.studentnr.backend.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private String email;
    private String firstName;
    private String midleName;
    private String sureName;
    private String address;
    private String postalCode;
    private String password;



    public String signUpUser(){
        if (password.length() < 6){
            return "/signup.jsf?faces-redirect=true&error=true";
        }

        boolean registered = false;
        try {
            registered = userService.createUser(email, firstName, midleName, sureName, address, postalCode, password);
        }catch (Exception e){
            //nothing to do
        }

        if(registered){

            UserDetails userDetails = userDetailsService.loadUserByUsername( email );
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    password,
                    userDetails.getAuthorities());

            authenticationManager.authenticate( token );

            if ( token.isAuthenticated() ) {
                SecurityContextHolder.getContext().setAuthentication( token );
            }

            return "/index.jsf?faces-redirect=true";
        } else {
            return "/signup.jsf?faces-redirect=true&error=true";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getMidleName() {
        return midleName;
    }

    public void setMidleName( String midleName ) {
        this.midleName = midleName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName( String sureName ) {
        this.sureName = sureName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode( String postalCode ) {
        this.postalCode = postalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
