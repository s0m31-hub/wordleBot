package org.nwolfhub.wordlebot.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;


@Data
@Builder
public class Word {
    @Id
    private String id;
    private Long creator;
    private String word;
    private Keyboard keyboard;
    private Boolean isPublic;
}
