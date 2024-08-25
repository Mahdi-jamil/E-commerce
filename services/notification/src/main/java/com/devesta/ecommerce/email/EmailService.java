package com.devesta.ecommerce.email;

import com.devesta.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal totalAmount,
            String orderReference
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, false, "utf-8");
        helper.setFrom("jamilmahdi77@gmail.com");
        helper.setTo(destinationEmail);
        helper.setSubject("Payment Successful");
        helper.setSentDate(new Date());

        String format = """
                Hello %s \n
                Your payment of %f for order reference %s has been successfully processed. \n
                Thank you for choosing our service. If you have any questions, feel free to contact us. \n
                Best regards \n
                Devesta - Mahdi Jamil
                """;
        String content = String.format(format, customerName, totalAmount, orderReference);
        helper.setText(content, false);

        mailSender.send(message);
        log.info("INFO - Email successfully sent to {}", destinationEmail);
    }

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal totalAmount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, false, "utf-8");
        helper.setFrom("jamilmahdi77@gmail.com");
        helper.setTo(destinationEmail);
        helper.setSubject("Order Confirmation");
        helper.setSentDate(new Date());

        String content = String.format("""
                    Order Details
                    Customer: %s
                    Order ID: %s
                                    
                    Product Name  Quantity  Price
                    %s
                                    
                    Total Amount: $%.2f
                    
                    Best regards \n
                    Devesta - Mahdi Jamil
                    """, customerName, orderReference, generateProductRows(products), totalAmount);

        helper.setText(content, false);

        mailSender.send(message);
        log.info("INFO - Email successfully sent to {}", destinationEmail);
    }

    private String generateProductRows(List<Product> products) {
        StringBuilder productRows = new StringBuilder();
        for (Product product : products) {
            productRows.append(String.format("%-20s %.2f  $%.2f%n", product.name(), product.quantity(), product.price()));
        }
        return productRows.toString();
    }

}
