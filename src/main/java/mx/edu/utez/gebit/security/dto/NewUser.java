package mx.edu.utez.gebit.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class NewUser {
    @NotBlank
    @Email
    private String username;
    @NotBlank
    private String password;

    //Por defecto crea un usuario normal
    //Si quiero un usuario Admin debo pasar este campo roles

    private Set<String> roles = new HashSet<>();


}
