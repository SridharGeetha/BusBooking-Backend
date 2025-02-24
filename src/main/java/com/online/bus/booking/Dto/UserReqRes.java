package com.online.bus.booking.Dto;

import com.online.bus.booking.Entity.MyUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReqRes {

    private int statusCode;
    private String error;
    private String message;
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String role;
    private MyUser user;
    private String token;
    
}
