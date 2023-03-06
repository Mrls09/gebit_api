package mx.edu.utez.gebit.controllers.building.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.building.Building;
import mx.edu.utez.gebit.models.laboratory.Laboratory;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BuildingDto {
    private Long id;
    @NotEmpty
    private String name;

    private List<Laboratory> laboratoryList;

    public Building getBuilding(){
        return new Building(
                getId(),
                getName(),
                getLaboratoryList()
        );
    }
}
