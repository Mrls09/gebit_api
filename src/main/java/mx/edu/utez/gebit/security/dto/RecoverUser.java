package mx.edu.utez.gebit.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RecoverUser {

    @Email
    private String username;

    private String password;

    private String tokenReset;
}
