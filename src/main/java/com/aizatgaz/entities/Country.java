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
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short id;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(name = "last_update", columnDefinition = "timestamp", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;


}
