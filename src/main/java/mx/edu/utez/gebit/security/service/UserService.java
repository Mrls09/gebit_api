package mx.edu.utez.gebit.security.service;

import mx.edu.utez.gebit.security.entity.User;
import mx.edu.utez.gebit.security.repository.UserRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
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

    public Boolean existByUser(String username){
        return userRepository.existsByUsername((username));
    }

    public void save(User user){
        userRepository.save(user);
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
}
