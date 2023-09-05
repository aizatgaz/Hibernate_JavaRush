package com.aizatgaz.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Stuff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Byte id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @ManyToOne
    @Column(name = "address_id")
    private Address address;

    @Column(columnDefinition = "blob")
    @Type(type = "blob")
    private Blob picture;

    @Column(length = 50)
    private String email;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Type(type = "tinyint")
    private boolean active;

    @Column(length = 16)
    private String username;

    @Column(length = 40)
    private String password;

    @Column(name = "last_update", columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
