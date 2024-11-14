package com.mistergold.mistergold.application.domain.message;

import com.mistergold.mistergold.application.domain.InfoActivation;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String id;
    private String senderName;
    private String senderEmail;
    private String text;
    private InfoActivation infoActivation;
}
