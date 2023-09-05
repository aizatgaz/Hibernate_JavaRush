package com.aizatgaz.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Byte id;

    @Column(columnDefinition = "char", length = 20, nullable = false)
    private String name;

    @Column(name = "last_update", columnDefinition = "timestamp", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;



}
