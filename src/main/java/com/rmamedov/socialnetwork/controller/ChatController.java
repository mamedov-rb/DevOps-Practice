package com.rmamedov.socialnetwork.controller;

import com.rmamedov.socialnetwork.model.Chat;
import com.rmamedov.socialnetwork.model.projection.ChatProjection;
import com.rmamedov.socialnetwork.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatProjection> findByName(@PathVariable final String name) {
        return ResponseEntity.ok(chatService.findByName(name));
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatProjection> findById(@PathVariable final String id) {
        return ResponseEntity.ok(chatService.findById(id));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ChatProjection>> findAll() {
        return ResponseEntity.ok(chatService.allChats());
    }

    @PostMapping(
            value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Chat> save(@Validated final @RequestBody Chat chat) {
        return ResponseEntity.ok(chatService.save(chat));
    }

}
