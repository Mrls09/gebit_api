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
import java.sql.Time;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BitacoraDto {
    private Long id;

    @NotEmpty
    private Timestamp created_at;

    private Timestamp finish_at;

    @NotEmpty
    private Computer computer;
    @NotEmpty
    private User user;

    public Bitacora getBitacora(){
        return new Bitacora(
                getId(),
                getCreated_at(),
                getFinish_at(),
                getComputer(),
                getUser()
        );
    }
}
