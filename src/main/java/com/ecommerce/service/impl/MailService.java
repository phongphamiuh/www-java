package com.ecommerce.service.impl;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.BadRequestException;
import com.ecommerce.model.entity.NotificationEmail;
import com.ecommerce.model.response.OrderResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
	private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmail notificationEmail){
        MimeMessagePreparator messagePreparator= mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("shopthietbigiadinh.iuh@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            javaMailSender.send(messagePreparator);
        }catch (MailException mailException){
            throw new BadRequestException("Exception occurred when sending mail to " +
                    notificationEmail.getRecipient());
        }
    }
    
    @Async
    public void sendMailWithOrder(NotificationEmail notificationEmail,OrderResponse orderResponse){
        MimeMessagePreparator messagePreparator= mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("shopthietbigiadinh.iuh@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.buildOrder(orderResponse));
        };
        try {
            javaMailSender.send(messagePreparator);
        }catch (MailException mailException){
            throw new BadRequestException("Exception occurred when sending mail to " +
                    notificationEmail.getRecipient());
        }
    }
}
