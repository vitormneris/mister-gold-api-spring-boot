package com.mistergold.mistergold.application.ports.out.email;

import com.mistergold.mistergold.adapters.email.dto.EmailDTO;

public interface SendEmailPort {
    void sendEmail(EmailDTO emailDTO);
}
