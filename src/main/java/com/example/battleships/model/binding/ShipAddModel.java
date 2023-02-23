package com.example.battleships.model.binding;

import com.example.battleships.domain.enums.CategoryType;
import com.example.battleships.validations.checkShipExistance.ValidateExistenceOfShip;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class ShipAddModel {

    @Size(min = 2, max = 10)
    @NotNull
    @ValidateExistenceOfShip
    private String name;
    @Positive
    @NotNull
    private Long health;
    @Positive
    @NotNull
    private Long power;
    @PastOrPresent
    @NotNull
    private Date created;
    @NotNull
    private CategoryType category;
}
