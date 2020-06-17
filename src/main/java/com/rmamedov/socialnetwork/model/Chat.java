package com.rmamedov.socialnetwork.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(of = {"name"})
public class Chat {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "name", updatable = false, unique = true)
    @NotBlank
    private String name;

    @Column(name = "created", updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

}
