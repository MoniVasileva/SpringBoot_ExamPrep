package com.example.battleships.services;

import com.example.battleships.domain.entities.Category;
import com.example.battleships.domain.enums.CategoryType;
import com.example.battleships.model.CategoryModel;
import com.example.battleships.repositorities.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void postConstruct() {
        if (this.categoryRepository.count() == 0) {
            this.categoryRepository.saveAllAndFlush(Arrays.stream(CategoryType.values())
                    .map(ct -> CategoryModel.builder()
                            .name(ct)
                            .description("fight me")
                            .build())
                    .map(ctm -> this.modelMapper.map(ctm, Category.class))
                    .toList());
        }
    }
}
