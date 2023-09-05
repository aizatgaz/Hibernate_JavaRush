package com.aizatgaz.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", columnDefinition = "smallint")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(columnDefinition = "decimal", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime paymentDate;

    @Column(name = "last_update", columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;


}
