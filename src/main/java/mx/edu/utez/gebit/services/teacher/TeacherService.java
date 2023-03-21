package mx.edu.utez.gebit.services.teacher;

import mx.edu.utez.gebit.models.teacher.Teacher;
import mx.edu.utez.gebit.models.teacher.TeacherRepository;
import mx.edu.utez.gebit.security.entity.Rol;
import mx.edu.utez.gebit.security.entity.User;
import mx.edu.utez.gebit.security.enums.RolName;
import mx.edu.utez.gebit.security.repository.UserRepository;
import mx.edu.utez.gebit.security.service.RolService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    RolService rolService;

    @Transactional(readOnly = true)
    public Response<List<Teacher>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional
    public Response<Teacher> getOne(Long id){
        if(this.repository.existsById(id)){
            return new Response<>(
                    this.repository.findById(id).get(),
                    false,
                    200,
                    "OK"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "Profesor no encontrado"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Teacher> insert(Teacher teacher){
        Optional<User> exist = this.userRepository.findByUsername(teacher.getUser().getUsername());
        if(exist.isPresent()){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Profesor ya se encuentra registrado"
            );
        }
        if(this.userRepository.existsByUsername(teacher.getUser().getUsername()))
            return new Response<>(
                    null,
                    true,
                    400,
                    "Usuario ya se encuentra registrado"
            );
        teacher.getUser().setPassword(
                encoder.encode(
                        teacher.getUser().getPassword()
                )
        );
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_TEACHER).get());
        teacher.getUser().setRoles(roles);
        return new Response<>(
                this.repository.saveAndFlush(teacher),
                false,
                200,
                "Profesor registrado correctamente"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Teacher> update(Teacher teacher){
        Teacher teacherUpdate = repository.findById(teacher.getId()).get();
        if(!this.repository.existsById(teacher.getId())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Profesor no encontrado"
            );
        }
        if(teacher.getName()!=null){
            teacherUpdate.setName(teacher.getName());
        }
        if(teacher.getLastname()!=null){
            teacherUpdate.setLastname(teacher.getLastname());
        }
        return new Response<>(
                this.repository.saveAndFlush(teacherUpdate),
                false,
                200,
                "Se ha actualizado correctamente el profesor"
        );
    }
}
