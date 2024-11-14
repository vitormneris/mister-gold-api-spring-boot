package com.mistergold.mistergold.adapters.email.service;

import com.mistergold.mistergold.adapters.email.dto.EmailDTO;
import com.mistergold.mistergold.application.ports.out.email.SendEmailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements SendEmailPort {
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailDTO.from());
        message.setTo(emailDTO.to());
        message.setSubject(emailDTO.subject());
        message.setText(emailDTO.text());

        mailSender.send(message);
    }
}
