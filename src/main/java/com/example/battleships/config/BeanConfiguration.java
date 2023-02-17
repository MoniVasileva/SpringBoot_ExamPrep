package com.example.battleships.config;

import com.example.battleships.domain.helpers.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public LoggedUser loggedUser() {
        return new LoggedUser();
    }
}
