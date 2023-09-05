package com.aizatgaz.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "film_text")

@Getter
@Setter
@NoArgsConstructor
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Short id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @OneToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
}
