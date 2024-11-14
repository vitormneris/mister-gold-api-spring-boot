package com.mistergold.mistergold.adapters.persistence.entities.message;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mensagens")
public class MessageEntity {
    @Id
    private String id;
    @Field(name = "nome_enviador")
    private String senderName;
    @Field(name = "email_enviador")
    private String senderEmail;
    @Field(name = "texto")
    private String text;
    @Field(name = "status_ativacao")
    private InfoActivationEntity infoActivation;
}
