package com.mistergold.mistergold.application.services.message;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.message.Message;
import com.mistergold.mistergold.application.ports.in.message.SaveMessageUseCase;
import com.mistergold.mistergold.application.ports.out.message.SaveMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SaveMessageService implements SaveMessageUseCase {
    private final SaveMessagePort saveMessagePort;

    @Override
    public Message save(Message message) {
        message.setId(null);

        InfoActivation infoActivation = InfoActivation.builder()
                .creationDate(Instant.now())
                .isActive(true)
                .build();

        message.setInfoActivation(infoActivation);

        return saveMessagePort.save(message);
    }
}
