package mx.edu.utez.gebit.controllers.teacher.teacherDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.teacher.Teacher;
import mx.edu.utez.gebit.security.entity.User;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeacherDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private Boolean status;

    private User user;

    public Teacher getTeacher(){
        return new Teacher(
                getId(),
                getName(),
                getLastname(),
                getStatus(),
                getUser()
        );
    }
}
