package mx.edu.utez.gebit.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.gebit.controllers.teacher.teacherDto.TeacherDto;
import mx.edu.utez.gebit.models.student.Student;
import mx.edu.utez.gebit.models.teacher.Teacher;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;


    @NotNull
    @ManyToMany
    @JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public User(){

    }
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id",unique = true)
    @JsonIgnore
    private Student student;

    @OneToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id",unique = true)
    @JsonIgnore
    private Teacher teacher;

}
