package com.mistergold.mistergold.application.services.client;

import com.mistergold.mistergold.adapters.email.dto.EmailDTO;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.in.client.SearchClientUseCase;
import com.mistergold.mistergold.application.ports.out.client.SaveClientPort;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;

import com.mistergold.mistergold.application.ports.out.client.UpdateClientPort;
import com.mistergold.mistergold.application.ports.out.email.SendEmailPort;
import com.mistergold.mistergold.application.ports.out.order.SearchOrderPort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchClientService implements SearchClientUseCase {
    private final SearchClientPort searchClientPort;
    private final SearchOrderPort searchOrderPort;
    private final UpdateClientPort updateClientPort;
    private final SendEmailPort sendEmailPort;

    @Override
    public PageResponse<Client> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name) {
        return  searchClientPort.findByPagination(isActive, page, pageSize, name);
    }

    @Override
    public Client findById(String id) {
        Client client = searchClientPort.findById(id);
        Set<Order> orders = new HashSet<>();
        client.getOrder().forEach(order -> orders.add(searchOrderPort.findById(order.getId())));
        client.setOrder(orders);
        return client;
    }

    @Override
    public void recoveryPasswordGenerator(String email) {
        Client client = searchClientPort.findByEmail(email);
        Random generator = new Random();

        String code = String.format("%06d", generator.nextInt(1000000));

        EmailDTO emailDTO = EmailDTO.builder()
                .from("mistergoldenterprise@gmail.com")
                .to(client.getEmail())
                .subject("Olá, " + client.getName() + ", tudo bem?")
                .text("Olá, recebemos uma solicitação sua sobre a troca de sua senha, volte ao site e digite este código " +
                        "para que você tenha autorização para a troca. \n CASO NÃO TENHA SIDO VOCÊ QUE SOLICITOU ESTA TROCA IGNORE ESSA MENSAGEM!\n\n" +
                        "CÓDIGO: " + code)
                .build();

        client.setCode(code);
        updateClientPort.update(client, client.getId());
        sendEmailPort.sendEmail(emailDTO);
    }
}
