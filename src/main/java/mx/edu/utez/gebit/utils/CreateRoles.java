package mx.edu.utez.gebit.utils;

import mx.edu.utez.gebit.security.entity.Rol;
import mx.edu.utez.gebit.security.entity.User;
import mx.edu.utez.gebit.security.enums.RolName;
import mx.edu.utez.gebit.security.repository.UserRepository;
import mx.edu.utez.gebit.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
/*
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args)throws Exception {
        Rol rolAdmin = new Rol(RolName.ROLE_ADMIN);
        Rol rolUser = new Rol(RolName.ROLE_USER);
        Rol rolTeacher = new Rol(RolName.ROLE_TEACHER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
        rolService.save(rolTeacher);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin")); // cambiar por una password segura
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        admin.setRoles(roles);
        userRepository.save(admin);
    }

}
*/