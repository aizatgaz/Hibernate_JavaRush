package com.aizatgaz.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte id;

    @Column(length = 25, nullable = false)
    private String name;

    @Column(name = "last_update", columnDefinition = "timestamp", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    private Set<Film> films;


}
