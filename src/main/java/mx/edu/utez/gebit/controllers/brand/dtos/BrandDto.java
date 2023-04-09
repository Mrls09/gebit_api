package mx.edu.utez.gebit.controllers.brand.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.brand.Brand;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BrandDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private Boolean status;


    public Brand getBrand(){
        return new Brand(
                getId(),
                getName(),
                getStatus(),
                null
        );
    }

}
