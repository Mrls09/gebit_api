package mx.edu.utez.gebit.controllers.bitacora.bitacoraDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.bitacora.Bitacora;
import mx.edu.utez.gebit.models.computer.Computer;
import mx.edu.utez.gebit.security.entity.User;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BitacoraDto {
    private Long id;

    private Timestamp created_at;

    private Computer computer;

    private User user;

    public Bitacora getBitacora(){
        return new Bitacora(
                getId(),
                getCreated_at(),
                getComputer(),
                getUser()
        );
    }
}
