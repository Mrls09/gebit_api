package mx.edu.utez.gebit.security.controller;

import mx.edu.utez.gebit.security.dto.RecoverUser;
import mx.edu.utez.gebit.security.entity.User;
import mx.edu.utez.gebit.security.service.UserService;
import mx.edu.utez.gebit.utils.Response;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/api-gebit/reset")
@CrossOrigin(origins = "http://localhost:3000")
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @PostMapping("/forgot_password")
    public Response<Object> processForgotPassword(@RequestBody RecoverUser user, Model model) {
        String email = user.getUsername();
        String token = RandomString.make(30);
        String url = "http://localhost:3000/";
        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = url + "reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("Message","We have sent a reset password link to your email. Please check." );
        } catch (UsernameNotFoundException ex){
            model.addAttribute("error", ex.getMessage());
        }catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
        return new Response<>(
                user.getUsername(),
                false,
                400,
                "CORREO ENVIADO"
        );
    }
    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(recipientEmail);

        String subject = "Link para restablecer tu contraseña de Gebit";

        String content = "<p>Hola,</p>"
                + "<p>Haz solicitado cambiar tu contraseña</p>"
                + "<p>Da click en el siguiente enlace</p>"
                + "<p><a href=\"" + link + "\">Cambiar contraseña</a></p>"
                + "<br>"
                + "<p>Si tu no haz solicitado un cambio de contraseña, "
                + "ignora este correo. </p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }
    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("token " , token);

        if(user == null) {
            model.addAttribute("message", "Token Invalido");
            return "message";
        }
        return "reset_password_form";
    }

    @PostMapping("/reset_password")
    public Response<String> processResetPassword(@RequestBody RecoverUser recoverUser, Model model) {
        String token = recoverUser.getTokenReset();
        String password = recoverUser.getPassword();

        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("title" , "Reset your password");

        if(user == null){
            model.addAttribute("message", "Token invalido");
            return new Response<>(
                    "TOKEN INVALIDO",
                    true,
                    200,
                    "TOKEN INVALIDO"
            );
        }
        userService.updatePassword(user, password);
        return new Response<>(
                "CONTRASEÑA CAMBIADA",
                false,
                400,
                "OK"
        );
    }

}
