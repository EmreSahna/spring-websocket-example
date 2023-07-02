package com.emresahna.websocketexample.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivateMessage {
    private String content;
    private String sender;
    private String receiver;
}
