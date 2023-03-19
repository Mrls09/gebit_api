package mx.edu.utez.gebit.security.service;

import mx.edu.utez.gebit.security.entity.User;
import mx.edu.utez.gebit.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUser(String username){
        return userRepository.findByUsername(username);
    }

    public Boolean existByUser(String username){
        return userRepository.existsByUsername((username));
    }

    public void save(User user){
        userRepository.save(user);
    }
}
