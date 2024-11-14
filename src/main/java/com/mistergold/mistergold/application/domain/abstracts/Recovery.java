package com.mistergold.mistergold.application.domain.abstracts;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Recovery {
    private String code;
    private String password;
}
