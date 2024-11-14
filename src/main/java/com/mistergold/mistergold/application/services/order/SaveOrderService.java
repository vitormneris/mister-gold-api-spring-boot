package com.mistergold.mistergold.application.services.order;

import com.mistergold.mistergold.adapters.email.dto.EmailDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.domain.order.OrderItem;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.in.order.SaveOrderUseCase;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;
import com.mistergold.mistergold.application.ports.out.client.UpdateClientPort;
import com.mistergold.mistergold.application.ports.out.email.SendEmailPort;
import com.mistergold.mistergold.application.ports.out.order.SaveOrderPort;
import com.mistergold.mistergold.application.ports.out.product.SearchProductPort;
import com.mistergold.mistergold.configuration.web.enums.OrderStatusEnum;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class SaveOrderService implements SaveOrderUseCase {
    private final SearchClientPort searchClientPort;
    private final SearchProductPort searchProductPort;
    private final UpdateClientPort updateClientPort;
    private final SendEmailPort sendEmailPort;
    private final SaveOrderPort saveOrderPort;

    @Override
    public Order save(Order order) {
        order.setId(null);
        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatusEnum.WAITING_PAYMENT);
        Client client = searchClientPort.findById(order.getClient().getId());
        order.setClient(client);

        order.getItems().forEach(orderItem -> {
            Product product = searchProductPort.findById(orderItem.getProduct().getId());
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(orderItem.getQuantity());
        });

        InfoActivation infoActivation = InfoActivation.builder()
            .creationDate(Instant.now())
            .isActive(true)
            .build();

        order.setInfoActivation(infoActivation);

        Order orderSaved = saveOrderPort.save(order);
        if (client.getOrder() == null) client.setOrder(new HashSet<>());
        client.getOrder().add(orderSaved);
        updateClientPort.update(client, order.getClient().getId());

        StringBuilder content = new StringBuilder();
        for (OrderItem orderItem : order.getItems()) {
            content.append("Nome do produto: ").append(orderItem.getProduct().getName())
                    .append("\n")
                    .append("Preço do produto: ").append(orderItem.getPrice())
                    .append("\n")
                    .append("Quantidade do produto: ").append(orderItem.getQuantity())
                    .append("\n")
                    .append("Valor total: ").append(orderItem.getPrice() * orderItem.getQuantity())
                    .append("\n").append("\n");
        }

        content.append("Preço total do pedido: ").append(order.totalPrice()).append("\n");
        content.append("Momento: ").append(order.getMoment()).append("\n");
        content.append("Status: ").append(order.getOrderStatus().getMessage()).append("\n");

        EmailDTO emailDTO = EmailDTO.builder()
                .from("mistergoldenterprise@gmail.com")
                .to(client.getEmail())
                .subject("Olá, " + client.getName()  + ", tudo bem? seu pedido acabou de ser realizado!")
                .text("Seu pedido:\n\n" + content)
                .build();

        sendEmailPort.sendEmail(emailDTO);
        return orderSaved;
    }
}
