package com.mistergold.mistergold.adapters.persistence.entities.product;

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
@Document(collection = "produtos")
public class ProductEntity {
    @Id
    private String id;
    @Field(name = "nome")
    private String name;
    @Field(name = "url_imagem")
    private String imageUrl;
    @Field(name = "descricao")
    private String description;
    @Field(name = "preco")
    private Double price;
    @Field(name = "quantidade")
    private Integer quantity;
    @Field(name = "categorias_id")
    private Set<String> categoriesId = new HashSet<>();
    @Field(name = "status_ativacao")
    private InfoActivationEntity infoActivation;
}
