package com.rmamedov.socialnetwork.repository;

import com.rmamedov.socialnetwork.model.Chat;
import com.rmamedov.socialnetwork.model.projection.ChatProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ChatRepository extends CrudRepository<Chat, String> {

    @Query("SELECT c FROM Chat c")
    Set<ChatProjection> findAllOrderByCreatedDesc();

    Optional<ChatProjection> findByName(String name);

    @Query("SELECT c FROM Chat c WHERE c.id = :id")
    Optional<ChatProjection> findChatProjectionById(@Param("id") String id);

}
