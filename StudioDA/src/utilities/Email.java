package utilities;

import java.net.InterfaceAddress;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class Email {
	//email: thatsailamkhi09@gmail.com
	//fupzmnfrcwnbyvpe
	
	public static void main(String[] args) {
		final String from = "thatsailamkhi09@gmail.com";
		final String pass = "fupzmnfrcwnbyvpe";
		
		final String to = "vankiet19992003@gmail.com";
		
		Properties props =  new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.STARTTLS.EnableSsl", "true");
		
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, pass);
			}
		};
		
		Session session = Session.getInstance(props, auth);
		
		MimeMessage message = new MimeMessage(session);
		try {
			message.addHeader("Content-type", "text; charset=UTF-8");
			message.setFrom(from);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			message.setSubject("Demo");
			message.setSentDate(new Date());
			// email nhan phan hoi
			//message.setReplyTo(InternetAddress.parse(to, false));
			
			message.setText("body", "UTF-8");
			
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

	
}
