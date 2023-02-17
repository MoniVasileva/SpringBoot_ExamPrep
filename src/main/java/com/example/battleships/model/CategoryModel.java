package com.example.battleships.model;

import com.example.battleships.domain.enums.CategoryType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryModel {
    private Long id;
    private CategoryType name;
    private String description;
}
