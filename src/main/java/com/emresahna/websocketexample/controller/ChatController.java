package com.emresahna.websocketexample.controller;

import com.emresahna.websocketexample.models.Message;
import com.emresahna.websocketexample.models.PrivateMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message greeting(@Payload Message message) {
        return message;
    }

    @MessageMapping("/chat.sendPrivateMessage")
    public void sendMessageToUser(@Payload PrivateMessage message) {
        String username = message.getReceiver();
        messagingTemplate.convertAndSendToUser(username, "/queue/messages", message);
    }
}
