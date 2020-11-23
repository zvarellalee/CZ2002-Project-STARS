/**
 * Notification Manager for handling of emails
 * @version 1.0
 * @since 2020-11-20
 */
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
	/**
	 * Sends an email to the specified email regarding successful course registration
	 * @param email Student's email
	 * @param student Student to email
	 * @param courseCode code of course that has been registered
	 * @param messageBody Message to include in the body
	 * @return
	 */
	public static boolean sendEmail(String email, Student student, String courseCode, String messageBody) {
		final String sender_username = "username";
		final String sender_password = "password";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sender_username, sender_password);
					}
				  });
		boolean status = false;
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Course " + courseCode + " Registered");
			message.setText("Hello " + student.getFirstName() + "! " + messageBody);
			
			Transport.send(message);
			
			System.out.println("Email sent successfully");
			status = true;
			
		}
		catch (MessagingException e) {
			e.printStackTrace();
		}
		return status;
	}
}