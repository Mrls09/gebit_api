package mx.edu.utez.gebit.security.service;

import mx.edu.utez.gebit.security.entity.User;
import mx.edu.utez.gebit.security.repository.UserRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Optional<User> getByUser(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<User> updatePassword(String username,String password, String newPass){
        User user = userRepository.findByUsername(username).get();
        if(!userRepository.existsByUsername(username)){
            return new Response<>(
                    null,
                    true,
                    400,
                    "User no encontrado"
            );
        }
        if (!passwordEncoder.matches(password, user.getPassword())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Password Erronea"
            );
        }
        user.setPassword(passwordEncoder.encode(newPass));
        return new Response<>(
                this.userRepository.saveAndFlush(user),
                false,
                200,
                "Contraseña cambiada correctamente"
        );
    }

    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }else{
            throw  new UsernameNotFoundException("Could not find any user with the username " + email);
        }
    }
    public User getByResetPasswordToken(String token){
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        String encodePassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodePassword);

        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }
}
