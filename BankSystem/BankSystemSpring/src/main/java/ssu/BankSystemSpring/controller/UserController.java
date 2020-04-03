package ssu.BankSystemSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ssu.BankSystemSpring.entity.JwtRequest;
import ssu.BankSystemSpring.entity.User;
import ssu.BankSystemSpring.security.JwtToken;
import ssu.BankSystemSpring.service.JwtUserDetailsService;
import ssu.BankSystemSpring.service.UserService;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtToken jwtToken;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService, JwtToken jwtToken) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtToken = jwtToken;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/signup")
    public Boolean create(@RequestBody User body) throws ValidationException {
        if (userService.existsUserByUsername(body.getUsername())) {
            throw new ValidationException("username already existed");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(body.getPassword());
        userService.createUser(body.getUsername(), encodedPassword, body.getPhone(), body.getAddress());
        return true;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
