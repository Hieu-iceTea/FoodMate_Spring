package Hieu_iceTea.FoodMate_Spring.utilities.email.implement;

import Hieu_iceTea.FoodMate_Spring.utilities.email.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

//https://baeldung.com/java-email
@Service
public class EmailServiceImplement_JavaMail implements EmailService {

    //region - Config Fields -
    private final String host = "smtp.gmail.com";
    private final int port = 587;
    private final String username = "Demo.Hieu.iceTea@gmail.com";
    private final String password = "xxolhnrisazzytbj"; //FoodMate_Spring
    //endregion


    //region - Private Method -
    private Session getSession() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", host);
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); //Chú ý thêm dòng này. Tham khảo: https://stackoverflow.com/questions/16115453/javamail-could-not-convert-socket-to-tls-gmail

        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private Message createMail(String to, String subject, String msg, String pathName) throws Exception {

        Session session = this.getSession();

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(host)); //From_mail
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); //emailAddressReceived, to_mail
        message.setSubject(subject); //Mail Subject

        Multipart multipart = new MimeMultipart();

        // Văn bản thông thường
        //String msg = "This is my first email using JavaMailer";
        if (msg != null && !msg.isBlank()) {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart);
        }

        // Đính kèm file:
        if (pathName != null && !pathName.isBlank()) {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File(pathName)); //"pom.xml"
            multipart.addBodyPart(attachmentBodyPart); //Đính kèm file
        }

        message.setContent(multipart);

        return message;

    }
    //endregion


    //region - Public Method -
    public void sendMail(String to, String subject) {

        try {
            Message message = createMail(to, subject, "This is my first email using JavaMailer", null);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMail(String to, String subject, String content) {

        try {
            Message message = createMail(to, subject, content, null);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //endregion


}
