package sustech.learn.consult.config;

public interface EmailSender {

    void send(String email, String subject, String htmlText);

}
