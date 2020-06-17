package com.rmamedov.socialnetwork.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseModel {

    private final String message;

    private final LocalDateTime time;

}
