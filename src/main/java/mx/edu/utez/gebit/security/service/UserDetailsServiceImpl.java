package mx.edu.utez.gebit.security.service;

import mx.edu.utez.gebit.security.entity.User;
import mx.edu.utez.gebit.security.entity.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userService.getByUser(username).get();
        return UserMain.build(user);
    }


}
