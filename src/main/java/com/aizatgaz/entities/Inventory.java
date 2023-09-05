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
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "last_update", columnDefinition = "timestamp", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;


}
