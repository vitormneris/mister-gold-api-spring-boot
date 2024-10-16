package com.mistergold.mistergold.adapters.persistence.entities.abstracts;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
public class UserEntityAbstract {
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
