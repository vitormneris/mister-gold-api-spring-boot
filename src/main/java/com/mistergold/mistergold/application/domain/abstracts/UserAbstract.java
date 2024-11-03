package com.mistergold.mistergold.application.domain.abstracts;

import com.mistergold.mistergold.application.domain.InfoActivation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAbstract {
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected InfoActivation infoActivation;
}
