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
                + "<body style='font-family: Arial, sans-serif; color: #333;'>"
                + "<div style='max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
                + "<h2 style='color: #007BFF;'>Welcome to Megacity Cab Services!</h2>"
                + "<p>Dear Driver,</p>"
                + "<p>We are pleased to inform you that your cab registration with <strong>Megacity Cab Services</strong> has been successfully approved. Welcome to our team!</p>"
                + "<h3 style='color: #007BFF;'>Your Login Credentials:</h3>"
                + "<ul>"
                + "<li><strong>Username:</strong> " + userName + "</li>"
                + "<li><strong>Password:</strong> " + password + "</li>"
                + "</ul>"
                + "<h3 style='color: #007BFF;'>Instructions to Get Started:</h3>"
                + "<ol>"
                + "<li>Log in to the <strong>Megacity Driver Portal</strong> using the credentials provided above.</li>"
                + "<li>Complete your profile by providing the necessary details.</li>"
                + "<li>Ensure your vehicle details are up-to-date and accurate.</li>"
                + "<li>Familiarize yourself with the platform and review the driver guidelines.</li>"
                + "<li>Start accepting ride requests and providing excellent service to our customers.</li>"
                + "</ol>"
                + "<h3 style='color: #007BFF;'>Important Notes:</h3>"
                + "<ul>"
                + "<li>Keep your login credentials secure and do not share them with anyone.</li>"
                + "<li>If you encounter any issues or have questions, please contact our support team at <a href='mailto:support@megacity.com'>support@megacity.com</a>.</li>"
                + "<li>Ensure your vehicle meets all safety and regulatory requirements before starting your service.</li>"
                + "</ul>"
                + "<p>We are excited to have you on board and look forward to working with you to provide exceptional service to our customers.</p>"
                + "<p>Best Regards,</p>"
                + "<p><strong>The Megacity Cab Services Team</strong></p>"
                + "<p>Email: <a href='mailto:support@megacity.com'>support@megacity.com</a></p>"
                + "<p>Phone: +1 (555) 123-4567</p>"
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
