package com.cabservice.MegaCity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
        private JavaMailSender mailSender;
             public void sendApprovalEmail(String to, String userName, String password) {
        try {
            System.out.println("Attempting to send email to: " + to);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("pasansasmika333@gmail.com"); // Set the "from" address explicitly
            helper.setTo(to);
            helper.setSubject("Cab Registration Approved - Welcome to Megacity Cab Services");

            // HTML Content for the Email
            String htmlContent = "<html>"
            + "<head>"
            + "<style>"
            + "    body { font-family: Inconsolata, monospace; color: #333; margin: 0; padding: 0; background-color: #f4f4f4; }"
            + "    .email-container { max-width: 600px; margin: 20px auto; padding: 20px; background-color: #ffffff; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }"
            + "    h2 { color: #007BFF; font-size: 24px; margin-bottom: 20px; }"
            + "    h3 { color: #007BFF; font-size: 20px; margin-top: 20px; margin-bottom: 10px; }"
            + "    p { font-size: 16px; line-height: 1.6; margin-bottom: 15px; }"
            + "    ul, ol { margin: 15px 0; padding-left: 20px; }"
            + "    li { margin-bottom: 10px; }"
            + "    .credentials { background-color: #f9f9f9; padding: 15px; border-left: 4px solid #007BFF; margin: 20px 0; }"
            + "    .credentials strong { color: #007BFF; }"
            + "    .footer { margin-top: 30px; padding-top: 20px; border-top: 1px solid #ddd; text-align: center; color: #777; font-size: 14px; }"
            + "    .footer a { color: #007BFF; text-decoration: none; }"
            + "    .footer a:hover { text-decoration: underline; }"
            + "</style>"
            + "</head>"
            + "<body>"
            + "<div class='email-container'>"
            + "    <h2>Welcome to Megacity Cab Services!</h2>"
            + "    <p>Dear Driver,</p>"
            + "    <p>We are pleased to inform you that your cab registration with <strong>Megacity Cab Services</strong> has been successfully approved. Welcome to our team!</p>"
            + "    <div class='credentials'>"
            + "        <h3>Your Login Credentials:</h3>"
            + "        <ul>"
            + "            <li><strong>Username:</strong> " + userName + "</li>"
            + "            <li><strong>Password:</strong> " + password + "</li>"
            + "        </ul>"
            + "    </div>"
            + "    <h3>Instructions to Get Started:</h3>"
            + "    <ol>"
            + "        <li>Log in to the <strong>Megacity Driver Portal</strong> using the credentials provided above.</li>"
            + "        <li>Complete your profile by providing the necessary details.</li>"
            + "        <li>Ensure your vehicle details are up-to-date and accurate.</li>"
            + "        <li>Familiarize yourself with the platform and review the driver guidelines.</li>"
            + "        <li>Start accepting ride requests and providing excellent service to our customers.</li>"
            + "    </ol>"
            + "    <h3>Important Notes:</h3>"
            + "    <ul>"
            + "        <li>Keep your login credentials secure and do not share them with anyone.</li>"
            + "        <li>If you encounter any issues or have questions, please contact our support team at <a href='mailto:support@megacity.com'>support@megacity.com</a>.</li>"
            + "        <li>Ensure your vehicle meets all safety and regulatory requirements before starting your service.</li>"
            + "    </ul>"
            + "    <p>We are excited to have you on board and look forward to working with you to provide exceptional service to our customers.</p>"
            + "    <div class='footer'>"
            + "        <p>Best Regards,</p>"
            + "        <p><strong>The Megacity Cab Services Team</strong></p>"
            + "        <p>Email: <a href='mailto:support@megacity.com'>support@megacity.com</a></p>"
            + "        <p>Phone: +1 (555) 123-4567</p>"
            + "    </div>"
            + "</div>"
            + "</body>"
            + "</html>";

            helper.setText(htmlContent, true); // Set the email content as HTML
            mailSender.send(message);
            System.out.println("Email successfully sent to: " + to);

        } catch (MessagingException e) {
            System.err.println("Error while sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
