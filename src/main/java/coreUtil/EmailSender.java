package coreUtil;

import java.io.File;
import java.util.Properties;

import constants.FrameworkConstants;
//import coreUtil.PropertyUtil;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailSender {

	public static void sendEmail() {

		final String senderEmail = "qa.testing0418@gmail.com";
		final String appPassword = "poookckuxocnlpbs";
		final String recipientEmail = "qa.testing0418@gmail.com";

		// SMTP Server Properties
		Properties prop = new Properties();

		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.port", "587");
		

		// Create session with authentication
		Session session = Session.getInstance(prop, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});

		session.setDebug(true);

		try {

			// Create Email message
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Test Email from QA Automation");
//			message.setText("Dear User,\n\nPlease find the Test Result \n\n Regards,\nQA Team");
			
			
			// Email Body Part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Dear User,\n\nPlease find the Test Result\n\nRegards,\nQA Team");
			
			// Attachment Part
			MimeBodyPart attachmentPart = new MimeBodyPart();
			String filePath = FrameworkConstants.getExtentReportpath();
//			String filePath = System.getProperty("user.dir") + "/reports/report.html";
			
			System.out.println("Attachment path is - " + filePath);
			
			attachmentPart.attachFile(new File(filePath));
			
			//Combine Body and Attachment Parts
			MimeMultipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(textPart);
			multiPart.addBodyPart(attachmentPart);
			message.setContent(multiPart);

			// Send Email
			Transport.send(message);

			System.out.println("Email sent successfully !!");
		}

		catch (Exception e) {

			e.printStackTrace();
		}
	}
}
