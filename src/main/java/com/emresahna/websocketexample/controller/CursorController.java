package com.emresahna.websocketexample.controller;

import com.emresahna.websocketexample.models.CursorLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CursorController {

    @MessageMapping("/cursor")
    @SendTo("/topic/cursor")
    public CursorLocation sendCursorLocation(@Payload CursorLocation location) {
        return location;
    }
}