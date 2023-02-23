package com.example.battleships.services;

import com.example.battleships.model.ShipModel;
import com.example.battleships.model.UserModel;
import com.example.battleships.model.UserWithShipsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleService {
    private final UserService userService;
    private final ShipService shipService;

    @Autowired
    public BattleService(UserService userService, ShipService shipService) {
        this.userService = userService;
        this.shipService = shipService;
    }


    public UserWithShipsModel getUserWithShips(Long id) {
        UserModel userModel = this.userService.findById(id);
        List<ShipModel> allByUserId = this.shipService.findAllByUserId(id);

        return UserWithShipsModel.builder()
                .userModel(userModel)
                .shipModels(allByUserId)
                .build();
    }

}