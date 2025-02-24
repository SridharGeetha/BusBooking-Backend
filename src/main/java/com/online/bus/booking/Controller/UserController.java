package com.online.bus.booking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.online.bus.booking.Dto.UserReqRes;
import com.online.bus.booking.Service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/add-new-user")
    public ResponseEntity<UserReqRes> registerUser(@RequestBody UserReqRes req){
        return ResponseEntity.ok(userService.registerUser(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserReqRes> login(@RequestBody UserReqRes req){
        return ResponseEntity.ok(userService.login(req));
    }
    
}
