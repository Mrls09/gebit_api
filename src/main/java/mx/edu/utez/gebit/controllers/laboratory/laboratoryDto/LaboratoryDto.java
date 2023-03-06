package mx.edu.utez.gebit.controllers.laboratory.laboratoryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.building.Building;
import mx.edu.utez.gebit.models.laboratory.Laboratory;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LaboratoryDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private Integer capacity;

    private Building building;

    public Laboratory getLaboratory(){
        return new Laboratory(
                getId(),
                getName(),
                getCapacity(),
                getBuilding()
        );
    }

}
