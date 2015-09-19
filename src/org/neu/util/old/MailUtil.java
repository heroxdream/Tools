package org.neu.util.old;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class MailUtil {

	private final static Logger log = LogManager.getLogger(MailUtil.class);

	private static ResourceBundle bundle = ResourceBundle.getBundle("mail");

	public static boolean send(String function, String subject, String content, String toEmails) {
		String[] emails = toEmails.split(",");
		return send(function, subject, content, emails);
	}
	
	public static boolean send(String from, String subject, String content, String... toEmails) {
		try {
			log.info("Prepare send mail to " + Arrays.toString(toEmails));
			Transport.send(getHtmlMessage(from, subject, content, toEmails));
			log.info("Send succeed...");
			return true;
		} catch (Exception e) {
			log.error("send mail error", e);
		}
		return false;
	}

	private static Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.transport.protocol", bundle.getString("mail.transport.protocol"));
		p.put("mail.smtp.host", bundle.getString("mail.smtp.host"));
		p.put("mail.smtp.port", bundle.getString("mail.smtp.port"));
		p.put("mail.smtp.auth", bundle.getString("mail.smtp.auth"));
		p.put("mail.smtp.socketFactory.class", bundle.getString("mail.smtp.socketFactory.class"));
		p.put("mail.smtp.socketFactory.fallback", bundle.getString("mail.smtp.socketFactory.fallback"));
		p.put("mail.smtp.socketFactory.port", bundle.getString("mail.smtp.socketFactory.port"));
		return p;
	}

	private static Message getMessage(final String function, String subject, String... toAddresses) throws Exception {
		Session sendMailSession = Session.getInstance(getProperties(), new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(bundle.getString(function + ".from.email"), bundle.getString(function + ".password"));
			}
		});
		Message message = new MimeMessage(sendMailSession);
		Address from = new InternetAddress(bundle.getString(function + ".from.email"), bundle.getString(function + ".from.name"));
		message.setFrom(from);
		Address[] to = new InternetAddress[toAddresses.length];
		for (int i = 0; i < toAddresses.length; i++) {
			to[i] = new InternetAddress(toAddresses[i]);
		}
		message.setRecipients(Message.RecipientType.TO, to);
		message.setRecipients(Message.RecipientType.BCC, getBCCAddress());
		message.setSubject(subject);
		message.setSentDate(new Date());
		return message;
	}

	private static Address[] getBCCAddress() throws AddressException {
		String bcc = bundle.getString("BCC");
		if (bcc != null) {
			String[] bccAddresses = bcc.split(",");
			Address[] addresses = new Address[bccAddresses.length];
			for (int i = 0; i < addresses.length; i++) {
				addresses[i] = new InternetAddress(bccAddresses[i]);
			}
			return addresses;
		}
		return new Address[0];
	}

	private static Message getHtmlMessage(String function, String subject, String content, String... toAddresses) throws Exception {
		Message message = getMessage(function, subject, toAddresses);
		Multipart mainPart = new MimeMultipart();
		BodyPart html = new MimeBodyPart();
		html.setContent(content, "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		message.setContent(mainPart);
		return message;
	}
	


}
