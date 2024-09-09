package com.mistergold.mistergold.adapters.persistence.entities.user;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class UserEntity {
    @Id
    private String id;
    @Field(name = "nome")
    private String name;
    private String email;
    @Field(name = "senha")
    private String password;
    @Field(name = "status_de_ativacao")
    private InfoActivationEntity infoActivation;
}
