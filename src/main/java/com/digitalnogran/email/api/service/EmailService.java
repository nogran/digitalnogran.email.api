package com.digitalnogran.email.api.service;

import com.digitalnogran.email.api.domain.model.Contact;
import com.digitalnogran.email.api.domain.model.EmailDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class EmailService {
    private final ContatoService contatoService;
    private final EmailSenderService emailSenderService;

    public boolean isMailSent(Contact contact, String from, String subject) {
        try {
            contatoService.save(contact);
            EmailDetails emailDetails = contatoService.buildEmail(contact, from, subject);
            emailSenderService.sendSimpleMail(emailDetails);
            return true;
        } catch (Exception e) {
            log.error("Error while processing email.", e);
            return false;
        }
    }
}
