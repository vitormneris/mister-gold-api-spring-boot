package com.mistergold.mistergold.application.domain.user;

import com.mistergold.mistergold.application.domain.InfoActivation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private InfoActivation infoActivation;
}
