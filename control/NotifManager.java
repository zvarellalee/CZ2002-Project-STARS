package Control;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import Entities.Course;
import Entities.Student;

public class NotifManager {
	private static final String sender_email = "tester_email";
    private static final String sender_password = "password123";
		
	public static void sendEmail(Student student, String courseCode){
		String email = student.getEmail();
		InternetAddress student_email = new InternetAddress(email);
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true"); 
				
		Authenticator auth = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender_email, sender_password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender_email));
			msg.addRecipient(Message.RecipientType.TO, student_email);
			msg.setSubject("Test email");
			msg.setText("Does it work?");
			Transport.send(msg);
			}
		catch (AddressException ae) {
			ae.printStackTrace();
		}
		catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}