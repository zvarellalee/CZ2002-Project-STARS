package control;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotifManager {
	private static final String sender_email = "tester_email";
	private static final String sender_password = "password123";
		
	public static boolean sendEmail(String email, String courseCode, String body){
		
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sender_email, sender_password);
					}
				  });
		boolean status = false;
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender_email));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Hello student");
			message.setText("body" + courseCode);
			
			Transport.send(message);
			
			System.out.println("Email sent");
			status = true;
			
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return status;
	}
}
