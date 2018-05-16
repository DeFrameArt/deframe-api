package com.deframe.api.utils.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.deframe.api.utils.ProjectConstants;

import javax.mail.internet.AddressException;

public class EmailUtility {

	public static void main(String[] args) {
		//URL path = EmailUtility.class.getResource("mailer.html");
		//File f = new File(path.getFile());
		//reader = new BufferedReader(new FileReader(f));
		new EmailUtility().sendWelcomeMail("bagankur@gmail.com");
	}

	/**
	 * Send Welcome mail 
	 */
	public void sendWelcomeMail(String recipientEmail) {

		try {
			String recipient_mail_id = recipientEmail;
			String mail_subject = "Welcome to DeFrame";

			Properties props = System.getProperties();
			String host_name = "smtp.gmail.com";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host_name);
			props.put("mail.smtp.user", ProjectConstants.SENDER_EMAIL);
			props.put("mail.smtp.password", ProjectConstants.SENDER_PASSWORD);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");

			
			/*props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");*/
			
			
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			try {
				// Set email data
				message.setFrom(new InternetAddress(ProjectConstants.SENDER_EMAIL));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient_mail_id));
				message.setSubject(mail_subject);
				MimeMultipart multipart = new MimeMultipart();
				BodyPart messageBodyPart = new MimeBodyPart();

				Map<String, String> input = new HashMap<String, String>();
				input.put("Author", "java2db.com");
				input.put("Topic", "HTML Template for Email");
				input.put("Content In", "English");
				// HTML mail content
				String filePath = new File("mailer.html").getAbsolutePath();
				System.out.println(filePath);
				
				URL path = EmailUtility.class.getResource("mailer.html");
				File f = new File(path.getFile());
				System.out.println(f.getPath());
				//String htmlText = readEmailFromHtml(filePath + "/src/main/java/com/deframe/api/utils/mail/mailer.html",
						// input);
				String htmlText = readEmailFromHtml(f.getPath(), input);
				messageBodyPart.setContent(htmlText, "text/html");
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);
				Transport transport = session.getTransport("smtp");
				transport.connect(host_name, ProjectConstants.SENDER_EMAIL, ProjectConstants.SENDER_PASSWORD);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			} catch (MessagingException ex) {
				Logger.getLogger(EmailUtility.class.getName()).log(Level.SEVERE, null, ex);
			} catch (Exception ae) {
				ae.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Method to replace the values for keys
	protected String readEmailFromHtml(String filePath, Map<String, String> input) {
		String msg = readContentFromFile(filePath);
		try {
			Set<Entry<String, String>> entries = input.entrySet();
			for (Map.Entry<String, String> entry : entries)
				msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return msg;
	}

	private String readContentFromFile(String fileName) {
		StringBuffer contents = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			try {
				String line = null;
				while ((line = reader.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				reader.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return contents.toString();
	}

	private boolean validateEmail(String email) {

		boolean isValid = false;

		try {

			// Create InternetAddress object and validated the email address.

			InternetAddress internetAddress = new InternetAddress(email);

			internetAddress.validate();

			isValid = true;

		} catch (AddressException e) {

			e.printStackTrace();

		}

		return isValid;
	}

}