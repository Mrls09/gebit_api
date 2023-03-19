package mx.edu.utez.gebit.security.entity;

import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.gebit.security.enums.RolName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolName;
    public Rol(){
    }
    public Rol(RolName rolName) {
        this.rolName = rolName;
    }
}
