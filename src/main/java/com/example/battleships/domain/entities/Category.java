package com.example.battleships.domain.entities;

import com.example.battleships.domain.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    @Column(unique = true, nullable = false)
    private CategoryType name;
    @Column(columnDefinition = "TEXT")
    private String description;
}




