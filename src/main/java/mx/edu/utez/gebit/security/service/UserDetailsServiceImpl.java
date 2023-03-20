package mx.edu.utez.gebit.security.service;

import mx.edu.utez.gebit.security.entity.User;
import mx.edu.utez.gebit.security.entity.UserMain;
import mx.edu.utez.gebit.security.repository.UserRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userService.getByUser(username).get();
        return UserMain.build(user);
    }


}
