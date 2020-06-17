package com.rmamedov.sotialnetwork;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmamedov.socialnetwork.SocialNetworkApplication;
import com.rmamedov.socialnetwork.model.Chat;
import com.rmamedov.socialnetwork.repository.ChatRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = {SocialNetworkApplication.class})
@AutoConfigureMockMvc
class ChatFlowTest extends SpringBootTestMvcPerformer {

    private static final String CREATE_CHAT_URL = "/api/chat/save";
    private static final String FIND_ALL_CHATS_URL = "/api/chat/all";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ChatRepository chatRepository;

    @BeforeEach
    public void init() {
        chatRepository.deleteAll();
    }

    @Test
    public void when_create_chat_then_success() throws Exception {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final var chat = new Chat();
        chat.setName(name);
        performPost(CREATE_CHAT_URL, chat)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.created").isNotEmpty());
    }

    @Test
    public void when_find_all_chats_then_success() throws Exception {
        saveTwoChats();
        performGet(FIND_ALL_CHATS_URL)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].created").isNotEmpty())
                .andExpect(jsonPath("$[1].id").isNotEmpty())
                .andExpect(jsonPath("$[1].name").isNotEmpty())
                .andExpect(jsonPath("$[1].created").isNotEmpty());
    }

    private void saveTwoChats() {
        final String name1 = RandomStringUtils.randomAlphabetic(10);
        final String name2 = RandomStringUtils.randomAlphabetic(10);
        final var chat1 = new Chat();
        final var chat2 = new Chat();
        chat1.setName(name1);
        chat2.setName(name2);
        chatRepository.saveAll(Set.of(chat1, chat2));
    }

    private ResultActions performPost(final String url, final Object object) {
        try {
            return mockMvc.perform(
                    post(url)
                            .content(mapper.writeValueAsString(object))
                            .contentType(MediaType.APPLICATION_JSON)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResultActions performGet(final String url, final Object... vars) {
        try {
            return mockMvc.perform(get(url, vars));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
