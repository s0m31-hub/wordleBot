package org.nwolfhub.wordlebot.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class Keyboard {
    @Id
    private String id;
    private String name;
    private List<String> rows;
}
