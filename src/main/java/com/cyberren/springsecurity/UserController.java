/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberren.springsecurity;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author exk
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/authenticate")
    public Principal authenticate(Principal user) {
        System.out.println("user::::" + user);
        return user;
    }
}
