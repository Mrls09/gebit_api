package mx.edu.utez.gebit.controllers.reason.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.reason.Reason;
import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@Setter
@Getter
public class ReasonDto {
    private Long id;
    private String name;
    private Boolean status;

    public Reason getReason() {
        return new Reason(
                getId(),
                getName(),
                getStatus()
        );
    }
}
