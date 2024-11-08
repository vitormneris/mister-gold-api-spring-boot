package com.mistergold.mistergold.adapters.persistence.entities.category;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categorias")
public class CategoryEntity {
    @Id
    private String id;
    @Field(name = "nome")
    private String name;
    @Field(name = "imagem_url")
    private String imageUrl;
    @Field(name = "description")
    private String description;
    @Field(name = "produtos_id")
    private Set<String> productsId = new HashSet<>();
    @Field(name = "status_ativacao")
    private InfoActivationEntity infoActivation;
}
