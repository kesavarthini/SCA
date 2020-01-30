package com.publicissapient.lloyds.orderservice.api;

/**
 * This is the controller used for User Authentication.
 * All the user Request of controller class passed here.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.publicissapient.lloyds.orderservice.model.UserTokenRequest;
import com.publicissapient.lloyds.orderservice.model.UserTokenResponse;
import com.publicissapient.lloyds.orderservice.service.JWTUserDetailsService;
import com.publicissapient.lloyds.orderservice.util.JwtTokenUtil;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class UserAuthController {

    @Autowired
    private JWTUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * This is for create authentication request.
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserTokenRequest authenticationRequest) throws Exception {
        String token=null;
        if(authenticationRequest.getUsername().equals("guest")){
            UserDetails guetUserDetails =new org.springframework.security.core.userdetails.User(authenticationRequest.getUsername(), "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
            token = jwtTokenUtil.generateToken(guetUserDetails);
        }
        else {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());

            token = jwtTokenUtil.generateToken(userDetails);
        }

        return ResponseEntity.ok(new UserTokenResponse(token));
    }

}
