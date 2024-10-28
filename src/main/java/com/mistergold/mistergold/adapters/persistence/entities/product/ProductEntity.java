package com.mistergold.mistergold.adapters.persistence.entities.product;

import com.mistergold.mistergold.application.domain.InfoActivation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "produtos")
public class ProductEntity {
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
    @Field(name = "informacoes_ativacao")
    private InfoActivation infoActivation;
}
