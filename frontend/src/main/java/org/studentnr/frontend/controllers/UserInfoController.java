package org.studentnr.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.studentnr.backend.entities.Purchase;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.entities.User;
import org.studentnr.backend.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserInfoController implements Serializable {

    @Autowired
    private UserService userService;

    private User user;


    public String getUserName(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public User getUserInfoWithPurchases(){
        this.user = userService.getUser( getUserName(), true);
        return user;
    }

    public User getUserInfoWithoutPurchases(){
        this.user = userService.getUser( getUserName(), false);
        return user;
    }

    public boolean isPurchasesEmpty(User user){
        return user.getPurchases().size() > 0;
    }

}
