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
	
	// random mã 6 sốs
	public static String fakeOTP() {
		
		int code = (int) Math.floor(((Math.random() * 899999) + 100000));
		String otp = String.valueOf(code);
		return otp;
	}
	
	public static String messageMail = fakeOTP();

	public void mail(String to, String subject) throws MessagingException {
		// String subject là tiêu đề của mail
		String msg = messageMail;
		final String from = "thatsailamkhi09@gmail.com"; // địa chỉ gmail gửi đi
		
		final String password = "utrjakejbmocvoms"; // pass mật khẩu ứng dụng sau khi xác thực 2 bước

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// session.setDebug(true);
		Transport transport = session.getTransport();
		InternetAddress addressFrom = new InternetAddress(from);

		MimeMessage message = new MimeMessage(session);
		message.setSender(addressFrom);
		message.setSubject(subject);
		message.setContent(msg, "text/plain"); // nội dung
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		transport.connect();
		Transport.send(message);
		transport.close();
	}

}
