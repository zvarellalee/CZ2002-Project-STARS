package control;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entities.Student;

public class NotifManager {
		
	public static boolean sendEmail(String email, Student student, String courseCode) {
		
		final String sender_username = "tester_email";
		final String sender_password = "password123";
		
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
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Course " + courseCode + " Registered");
			message.setText("Hello " + student.getFirstName() + ", Course " + courseCode + " has been successfully registered and you are removed from the wait list.");
			
			Transport.send(message);
			
			System.out.println("Email sent successfully");
			status = true;
			
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return status;
	}
}
