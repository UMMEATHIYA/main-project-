package com.example.userservice.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.userservice.message.request.LoginForm;
import com.example.userservice.message.request.SignUpForm;
import com.example.userservice.message.response.JwtResponse;
import com.example.userservice.message.response.ResponseMessage;
import com.example.userservice.model.Role;
import com.example.userservice.model.RoleName;
import com.example.userservice.model.User;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.jwt.JwtProvider;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) throws MessagingException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getDomain());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
                    switch (role) {

                        case "pm":
                            Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                            roles.add(pmRole);

                            break;
                        default:
                            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                            roles.add(userRole);
                    }
                }
        );

        String mail = signUpRequest.getEmail();
        String username = signUpRequest.getUsername();
        String password = signUpRequest.getPassword();
        String name = signUpRequest.getName();
        String content = "<html>" +
                "<body>" +
                "<p>Hello "+name+",</p>" +
                "<p>You have been successfully registered on <strong>PROJECT MANAGEMENT</strong> portal!</p>" +
                "<p><strong>Your username is: </strong>"+username+"</p>"+
                "<p><strong>Your password is: </strong>"+password+"</p>"+
                "<p>Regards,</p>"+
                "<p>PROJECT MANAGEMENT TEAM</p>"+
                "</body>" +
                "</html>";

        user.setRoles(roles);
        userRepository.save(user);


        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/role/{name}")
    public List<User> getAllByRole(@PathVariable RoleName name)
    {
        List<User> l = new ArrayList<>();
        for(User u:userRepository.findByRolesName(name))
        {
            l.add(u);
        }
        return l;
    }


}
