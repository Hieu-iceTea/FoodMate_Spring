package Hieu_iceTea.FoodMate_Spring.utilities.email;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;


public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessageUsingTemplate(String to, String subject, String ...templateModel);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

    void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel)  throws IOException, MessagingException;

}
