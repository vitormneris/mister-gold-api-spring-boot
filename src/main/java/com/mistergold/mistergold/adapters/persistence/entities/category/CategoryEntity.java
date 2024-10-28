package com.mistergold.mistergold.adapters.persistence.entities.category;

import com.mistergold.mistergold.application.domain.InfoActivation;
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
    @Field(name = "status_de_ativacao")
    private InfoActivation infoActivation;
}
