package control;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import entities.Course;
import entities.Student;

public class NotifManager {
	private static final String sender_email = "tester_email";
	private static final String sender_password = "password123";
		
	public static void sendEmail(Student student, String courseCode){
		String email = student.getEmail();
		InternetAddress student_email = new InternetAddress(email);
		
		Properties props = new Properties();
		props.put("mail.smtp.user", sender_email);
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true"); 
				
		Session session = Session.getInstance(props, new javax.mail.Authenticator(sender_email, sender_password));
		
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender_email));
			msg.addRecipient(Message.RecipientType.TO, student_email);
			msg.setSubject("Test email");
			msg.setText("Does it work?");
			Transport.send(msg);
		}
		catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}
