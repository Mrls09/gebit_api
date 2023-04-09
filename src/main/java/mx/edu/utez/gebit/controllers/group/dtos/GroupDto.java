package mx.edu.utez.gebit.controllers.group.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.career.Career;
import mx.edu.utez.gebit.models.group.Group;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GroupDto {
    private Long id;
    @NotEmpty
    private Integer degree;
    @NotEmpty
    private String letter;
    @NotEmpty
    private Boolean status;
    @NotEmpty
    private Career career;


    public Group getGroup(){
        return new Group(
                getId(),
                getDegree(),
                getLetter(),
                getStatus(),
                getCareer()
        );
    }
}
