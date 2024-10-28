package com.mistergold.mistergold.application.domain.category;

import com.mistergold.mistergold.application.domain.InfoActivation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private InfoActivation infoActivation;
}
