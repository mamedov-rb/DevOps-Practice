package com.rmamedov.socialnetwork.service;

import com.rmamedov.socialnetwork.model.Chat;
import com.rmamedov.socialnetwork.model.projection.ChatProjection;
import com.rmamedov.socialnetwork.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Chat save(final Chat chat) {
        return chatRepository.save(chat);
    }

    @Transactional(readOnly = true)
    public ChatProjection findByName(final String name) {
        return chatRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Chat with name: '%s' Not Found.", name)));
    }

    @Transactional(readOnly = true)
    public ChatProjection findById(final String id) {
        return chatRepository.findChatProjectionById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Chat with id: '%s' Not Found.", id)));
    }

    @Transactional(readOnly = true)
    public Set<ChatProjection> allChats() {
        return chatRepository.findAllOrderByCreatedDesc();
    }

}
