package mx.edu.utez.gebit.controllers.computer.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.brand.Brand;
import mx.edu.utez.gebit.models.computer.Computer;
import mx.edu.utez.gebit.models.laboratory.Laboratory;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ComputerDto {
    private Long id;
    @NotEmpty
    private String serial;
    @NotEmpty
    private String model;
    private String description;
    private Boolean status;
    private Long numberPc;
    private Brand brand;

    private Laboratory laboratory;

    public Computer getComputer(){
        return new Computer(
                getId(),
                getSerial(),
                getModel(),
                getDescription(),
                getStatus(),
                getNumberPc(),
                getBrand(),
                getLaboratory()
        );
    }
}
