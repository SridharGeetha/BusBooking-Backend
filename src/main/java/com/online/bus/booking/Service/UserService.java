package com.online.bus.booking.Service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.online.bus.booking.Dto.UserReqRes;
import com.online.bus.booking.Entity.MyUser;
import com.online.bus.booking.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authorizationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserReqRes registerUser(UserReqRes request){
        UserReqRes respone = new UserReqRes();

        try {

            MyUser user = new MyUser();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole());
            
            MyUser res = userRepository.save(user);
            // respone.setUser(user);



            if(res.getUserId()>0){
                respone.setStatusCode(200);
                respone.setMessage("success");
                respone.setUsername(res.getUsername());
                respone.setEmail(res.getEmail());
                respone.setRole(res.getRole());
                respone.setUserId(res.getUserId());
            }
            
        } catch (Exception e) {
            respone.setStatusCode(500);
                respone.setMessage("error in new add user");
        }

        return respone;
    }

    public UserReqRes login(UserReqRes req){
            UserReqRes response = new UserReqRes();

            try {
               
            authorizationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
            var user = userRepository.findByEmail(req.getEmail()).orElseThrow();

            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefershToken(new HashMap<>(), user);
            response.setUserId(user.getUserId());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());
            response.setToken(jwt);
            response.setStatusCode(200);
            response.setMessage("Successfully Login");
            } catch (Exception e) {
               response.setStatusCode(500);
               response.setMessage(e.getMessage());
            }

            return response;
    }
    
}
