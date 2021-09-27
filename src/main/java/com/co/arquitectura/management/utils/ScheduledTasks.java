package com.co.arquitectura.management.utils;
import com.co.arquitectura.management.domain.repository.LoginRepository;
import com.co.arquitectura.management.domain.repository.model.database.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.text.SimpleDateFormat;
import java.util.List;


@Component
public class ScheduledTasks {

    final static Logger logger = Logger.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private RestTemplate restTemplate;
    private JavaMailSender javaMailSender;
    private LoginRepository loginRepository;

    @Autowired
    public ScheduledTasks(RestTemplate restTemplate, JavaMailSender javaMailSender, LoginRepository loginRepository) {
        this.restTemplate = restTemplate;
        this.javaMailSender = javaMailSender;
        this.loginRepository = loginRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void checkingTheServices() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            List<UserEntity> user = this.loginRepository.getAllUser();
            logger.info("Empezando a monitorear los intento de login");
            for (UserEntity tmp: user){
                logger.info("El siguiente usuario "+ tmp.getUser()+" registra los siguientes intentos "+ tmp.getCount());
                if(tmp.getCount() >= 2 ) {
                    logger.info("Se detecto intento de login sospechoso con la siguiente cuenta " + tmp.getEmail());
                    this.sendEmail(tmp);
                }
            }

        } catch (Exception e) {
            logger.error("Se presentaron problemas para enviar el servico", e);
        }
    }

    public void sendEmail(UserEntity user) throws javax.mail.MessagingException {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("");

        String html = "<html>\n" +
                "\n" +
                "<head>\n" +
                "\t<title>Alerta seguridad</title>\n" +
                "</head>\n" +
                "\n" +
                "<body style= \"background-color: black; height: 100vh; width: 100%; text-align: center;\">\n" +
                "\t\n" +
                "\t<div style= \"background-color: white; height: calc(100% - 8%); width: calc(100% - 8%);  margin-left:  32px; \n" +
                "\tmargin-right: 32px; margin-top: 32px; margin-bottom: 32px;\">\n" +
                "     \n" +
                "\n" +
                "\t <div style=\"margin-bottom: 44px; padding-top: 44px;\">\n" +
                "\t\t <span style=\"font-size: 24px; font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; font-weight: bold;\" > Sr(a)  " +user.getUser() + "</span>\n" +
                "\t </div>\n" +
                "\n" +
                "\t <div style=\"margin-left: 32px; margin-right: 32px; padding-bottom: 64px;\">\n" +
                "\t\t<span style=\"font-size: 24px; font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; font-weight: bold; color: brown;\" >Se ha intentado ingresar varias veces a nuestro sistema con una clave incorrecta. Por favor lo invitamos a cambiar la clave los mas pronto posible  </span>\n" +
                "\t</div>\n" +
                "\n" +
                "\t<div>\n" +
                "\t\t<img src=\"https://cdn.icon-icons.com/icons2/2534/PNG/512/insurance_security_alert_icon_152052.png\">\n" +
                "\t</div>\n" +
                "\t</div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        helper.setText(html, true);
        String[]  to =  {"marbyptt@gmail.com", "cristianarangodaza@gmail.com", "wilson.gb3@gmail.com", user.getEmail()};
        helper.setTo(to);
        javaMailSender.send(mimeMessage);
        logger.warn("Se envio un correo notificando una posible falla de seguridad en nuestro sistema");
    }
}