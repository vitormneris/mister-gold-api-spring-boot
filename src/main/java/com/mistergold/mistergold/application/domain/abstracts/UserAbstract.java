package com.mistergold.mistergold.application.domain.abstracts;

import com.mistergold.mistergold.application.domain.InfoActivation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAbstract {
    private String id;
    private String name;
    private String email;
    private String password;
    private InfoActivation infoActivation;
}
