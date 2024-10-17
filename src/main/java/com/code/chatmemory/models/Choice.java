package com.code.chatmemory.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Choice {
    private int index;
    private Message message;
    private Object logprobs;
    private String finish_reason;
}
