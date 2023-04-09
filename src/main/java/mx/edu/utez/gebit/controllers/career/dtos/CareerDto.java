package mx.edu.utez.gebit.controllers.career.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.career.Career;
import mx.edu.utez.gebit.models.group.Group;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CareerDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String abbreviation;
    private Boolean status;
    private List<Group> groupList;

    public Career getCareer(){
        return new Career(
                getId(),
                getName(),
                getAbbreviation(),
                getStatus(),
                getGroupList()
        );
    }
}
