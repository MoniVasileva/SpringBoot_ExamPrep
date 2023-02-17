package com.example.battleships.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ships")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @Size(min = 2, max = 10)
    private String name;
    @Column(nullable = false)
    private Long health;
    @Column
    private Long power;
    @Column
    private Date created;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;


}
