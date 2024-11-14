package com.mistergold.mistergold.application.services.message;

import com.mistergold.mistergold.application.domain.message.Message;
import com.mistergold.mistergold.application.ports.in.message.SearchMessageUseCase;
import com.mistergold.mistergold.application.ports.out.message.SearchMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SearchMessageService implements SearchMessageUseCase {
    private final SearchMessagePort searchMessagePort;

    @Override
    public Set<Message> findAll() {
        return searchMessagePort.findAll();
    }
}
