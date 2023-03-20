package mx.edu.utez.gebit.services.student;

import mx.edu.utez.gebit.controllers.student.studentDto.StudentDto;
import mx.edu.utez.gebit.models.student.Student;
import mx.edu.utez.gebit.models.student.StudentRepository;
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
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    RolService rolService;

    @Transactional(readOnly = true)
    public Response<List<Student>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional
    public Response<Student> getOne(Long id){
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
                "Estudiante no encontrado"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Student> insert(Student student){
        Optional<User> exist = this.userRepository.findByUsername(student.getUser().getUsername());
        if(exist.isPresent()){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Estudiante ya se encuentra registrado"
            );
        }
        if(this.userRepository.existsByUsername(student.getUser().getUsername()))
            return new Response<>(
                    null,
                    true,
                    400,
                    "El usuario ya se encuentra registrado"
            );
        //Encriptacion de la password
        student.getUser().setPassword(
                encoder.encode(
                        student.getUser().getPassword()
                )
        );
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        student.getUser().setRoles(roles);

        return new Response<>(
                this.repository.saveAndFlush(student),
                false,
                200,
                "Estudiante registrado correctamente"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Student> update(Student student){
        Student student1 = repository.findById(student.getId()).get();
        if(!this.repository.existsById(student.getId())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Estudiante no encontrado"
            );
        }
        /*if(student.getUser().getPassword() != null){
            student.getUser().setPassword(
                    encoder.encode(
                            student.getUser().getPassword()
                    )
            );
        }
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        student.getUser().setRoles(roles);*/
        if(student.getLastname()!=null){
            student1.setLastname(student.getLastname());
            System.out.println("SI TRAE APELLIDO");
        }
        if(student.getName()!=null){
            student1.setName(student.getName());
            System.out.println("SI TRAE NOMBRE");
        }

        return new Response<>(
                this.repository.saveAndFlush(student1),
                false,
                200,
                "Se ha actualizado correctamente el estudiante"
        );
    }

}
