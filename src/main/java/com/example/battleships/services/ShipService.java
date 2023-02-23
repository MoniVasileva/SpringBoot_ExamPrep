package com.example.battleships.services;

import com.example.battleships.domain.entities.Ship;
import com.example.battleships.domain.helpers.LoggedUser;

import com.example.battleships.model.CategoryModel;
import com.example.battleships.model.ShipModel;
import com.example.battleships.model.UserModel;
import com.example.battleships.model.binding.BattleShipsModel;
import com.example.battleships.model.binding.ShipAddModel;
import com.example.battleships.repositorities.ShipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ShipService(ShipRepository shipRepository, ModelMapper modelMapper, LoggedUser loggedUser, UserService userService, CategoryService categoryService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public void addShip(ShipAddModel addModel) {
        UserModel userModel = this.userService.findById(this.loggedUser.getId());
        CategoryModel categoryModel = this.categoryService.findByName(addModel.getCategory());

        Ship shipToSave = this.modelMapper.map(ShipModel.builder()
                        .category(categoryModel)
                        .created(addModel.getCreated())
                        .name(addModel.getName())
                        .health(addModel.getHealth())
                        .power(addModel.getPower())
                        .user(userModel)
                        .build(),
                Ship.class);

        this.shipRepository.saveAndFlush(shipToSave);
    }

    public List<ShipModel> findAllByUserId(Long id) {
        return this.shipRepository.findAllByUserId(id)
                .orElseThrow()
                .stream()
                .map(ship -> this.modelMapper.map(ship, ShipModel.class))
                .toList();
    }

    public void fight(BattleShipsModel battleShipsModel) {
        Ship loggedShip = this.shipRepository.findById(battleShipsModel.getLoggedUserShip()).orElseThrow();
        Ship notLoggedShip = this.shipRepository.findById(battleShipsModel.getNotLoggedUserShip()).orElseThrow();

        notLoggedShip.setHealth(notLoggedShip.getHealth() - loggedShip.getPower());

        if (notLoggedShip.getHealth() <= 0) {
            this.shipRepository.deleteById(battleShipsModel.getNotLoggedUserShip());
        } else {
            this.shipRepository.saveAndFlush(notLoggedShip);
        }
    }
}
