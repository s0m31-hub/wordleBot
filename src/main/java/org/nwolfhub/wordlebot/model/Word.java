package org.nwolfhub.wordlebot.model;


import org.springframework.data.annotation.Id;

import java.util.UUID;


public class Word {
    @Id
    private String id;
    private Long creator;
    private String word;
    private String allowedLetters;

}
