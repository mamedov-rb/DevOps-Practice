package com.rmamedov.socialnetwork.model.projection;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface ChatProjection {

    String getId();

    String getName();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    LocalDateTime getCreated();

}
