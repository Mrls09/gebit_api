package mx.edu.utez.gebit.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUser {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String newPass;

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
