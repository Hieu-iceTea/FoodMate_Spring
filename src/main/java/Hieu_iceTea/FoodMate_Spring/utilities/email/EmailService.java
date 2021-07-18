package Hieu_iceTea.FoodMate_Spring.utilities.email;

public interface EmailService {

    void sendMail(String to, String subject);

    void sendMail(String to, String subject, String content);

}
