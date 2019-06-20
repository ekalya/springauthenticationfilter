/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberren.springsecurity;

/**
 *
 * @author exk
 */
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping("/api/test")
public class AuthenticationTestController {
    @RequestMapping(
        method = RequestMethod.GET
    )
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("You can only see this after a successful login :)");
    }
    
}
