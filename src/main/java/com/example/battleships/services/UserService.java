package com.example.battleships.services;

import com.example.battleships.domain.entities.User;
import com.example.battleships.domain.helpers.LoggedUser;
import com.example.battleships.model.UserModel;
import com.example.battleships.repositorities.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserModel findByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username).orElse(new User()), UserModel.class);
    }

    public UserModel findById(Long id) {
        return this.modelMapper.map(this.userRepository.findById(id).orElse(new User()), UserModel.class);
    }

    public UserModel findByIdNot(Long id) {
        return this.modelMapper.map(this.userRepository.findByIdNot(id).orElse(new User()), UserModel.class);
    }

}