package spring.common.utils;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;



/**
 * Created by root on 27.02.17.
 */
public class Mailer {
    private static Logger logger = Logger.getLogger(Mailer.class);

        private static String username = "waitingformessage1@gmail.com";
        private static  String password = "123qweasdf";


        public static  void sendMail(String email, String subject, String text) {
            if (email==null||subject==null||text==null) {
                return;
            }
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress());
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject(subject);
                message.setText(text);

                Transport.send(message);
            } catch (MessagingException e) {
                logger.error(e);
            }
        }
    }

