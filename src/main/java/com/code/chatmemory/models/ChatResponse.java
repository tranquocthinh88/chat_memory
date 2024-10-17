package com.code.chatmemory.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private String system_fingerprint;
    private List<Choice> choices;
    private Usage usage;
}
