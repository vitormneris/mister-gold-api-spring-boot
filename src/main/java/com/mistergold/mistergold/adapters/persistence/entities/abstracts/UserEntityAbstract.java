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
    protected String id;
    @Field(name = "nome")
    protected String name;
    protected String email;
    @Field(name = "senha")
    protected String password;
    @Field(name = "status_ativacao")
    protected InfoActivationEntity infoActivation;
}
