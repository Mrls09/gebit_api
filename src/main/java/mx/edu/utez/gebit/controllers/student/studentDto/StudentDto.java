package mx.edu.utez.gebit.controllers.student.studentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.group.Group;
import mx.edu.utez.gebit.models.student.Student;
import mx.edu.utez.gebit.security.entity.User;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private Boolean status;

    private Group group;

    private User user;

    public Student getStudent(){
        return new Student(
                getId(),
                getName(),
                getLastname(),
                getStatus(),
                getGroup(),
                getUser()
        );
    }

}
