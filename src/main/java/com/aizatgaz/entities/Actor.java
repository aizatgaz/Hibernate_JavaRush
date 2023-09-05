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
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "last_update", columnDefinition = "timestamp", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    private Set<Film> films;


}
