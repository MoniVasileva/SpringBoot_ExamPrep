package com.example.battleships.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithShipsModel {

    private UserModel userModel;
    private List<ShipModel> shipModels;

}