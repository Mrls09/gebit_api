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
@CrossOrigin(origins = "http://192.168.137.90:3000")
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @PostMapping("/forgot_password")
    public Response<Object> processForgotPassword(@RequestBody RecoverUser user, Model model) {
        String email = user.getUsername();
        String token = RandomString.make(30);
        String url = "http://192.168.137.90:3000/";
        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = url + "reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
        } catch (UsernameNotFoundException ex){
            System.out.println("catch" + ex);
        }catch (UnsupportedEncodingException | MessagingException e) {
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

        /*String content = "<p>Hola,</p>"
                + "<p>Haz solicitado cambiar tu contraseña</p>"
                + "<p>Da click en el siguiente enlace</p>"
                + "<p><a href=\"" + link + "\">Cambiar contraseña</a></p>"
                + "<br>"
                + "<p>Si tu no haz solicitado un cambio de contraseña, "
                + "ignora este correo. </p>";*/

        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <title></title>\n" +
                "  <!--[if mso]>\n" +
                "  <noscript>\n" +
                "    <xml>\n" +
                "      <o:OfficeDocumentSettings>\n" +
                "        <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "      </o:OfficeDocumentSettings>\n" +
                "    </xml>\n" +
                "  </noscript>\n" +
                "  <![endif]-->\n" +
                "  <style>\n" +
                "    table, td, div, h1, p {font-family: Arial, sans-serif;}\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body style=\"margin:0;padding:0;\">\n" +
                "  <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;background:#ffffff;\">\n" +
                "    <tr>\n" +
                "      <td align=\"center\" style=\"padding:0;\">\n" +
                "        <table role=\"presentation\" style=\"width:602px;border-collapse:collapse;border:1px solid #cccccc;border-spacing:0;text-align:left;\">\n" +
                "          <tr>\n" +
                "            <td align=\"center\" style=\"padding:40px 0 30px 0;background:#70bbd9;\">\n" +
                "              <img src=\"https://assets.codepen.io/210284/h1.png\" alt=\"\" width=\"300\" style=\"height:auto;display:block;\" />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td style=\"padding:36px 30px 42px 30px;\">\n" +
                "              <table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n" +
                "                <tr>\n" +
                "                  <td style=\"padding:0 0 36px 0;color:#153643;\">\n" +
                "                    <h1 style=\"font-size:24px;margin:0 0 20px 0;font-family:Arial,sans-serif;\">Recuperación de contraseña GEBIT</h1>\n" +
                "                    <p><a href=\"" + link + "\">Cambiar contraseña</a></p>\n" +
                "                    <p style=\"margin:0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\"><a href=\"http://www.example.com\" style=\"color:#ee4c50;text-decoration:underline;\">In tempus felis blandit</a></p>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";

        //<p><a href=\"" + link + "\">Cambiar contraseña</a></p>


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
