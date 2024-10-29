package com.mistergold.mistergold.adapters.persistence.entities.administrator;

import com.mistergold.mistergold.adapters.persistence.entities.abstracts.UserEntityAbstract;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Document(collection = "administradores")
public class AdministratorEntity extends UserEntityAbstract {
}
