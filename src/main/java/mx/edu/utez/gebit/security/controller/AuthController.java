package mx.edu.utez.gebit.security.controller;

import mx.edu.utez.gebit.security.dto.JwtDto;
import mx.edu.utez.gebit.security.dto.LoginUser;
import mx.edu.utez.gebit.security.dto.NewUser;
import mx.edu.utez.gebit.security.entity.User;
import mx.edu.utez.gebit.security.jwt.JwtProvider;
import mx.edu.utez.gebit.security.repository.UserRepository;
import mx.edu.utez.gebit.security.service.RolService;
import mx.edu.utez.gebit.security.service.UserService;
import mx.edu.utez.gebit.utils.Response;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api-gebit/auth")
@CrossOrigin()
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    //Espera un json y lo convierte a tipo clase NewUser

    /*@PostMapping("/newUSer")
    public ResponseEntity<?> newUSer(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new Response<>(
                    null,

            ), HttpStatus.BAD_REQUEST);
        }
    }*/
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(),userDetails.getAuthorities());
        Map<String, Object> data = new HashMap<>();
        data.put("token", jwt);
        data.put("user", userDetails);
        System.out.println("TOKEN->" + jwt);
        return new ResponseEntity<>(jwtDto,HttpStatus.OK);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<Response<User>> changeUserPassword(@RequestBody LoginUser loginUser) {
        return new ResponseEntity<>(
                this.userService.updatePassword(loginUser.getUsername(),loginUser.getPassword(), loginUser.getNewPass()),
                HttpStatus.OK
        );
    }

}
