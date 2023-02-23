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
                    .map(enumValue -> CategoryModel.builder()
                            .name(enumValue)
                            .description("fight me")
                            .build())
                    .map(categoryModel -> this.modelMapper.map(categoryModel, Category.class))
                    .toList());
        }
    }

    public CategoryModel findByName(CategoryType name) {
        return this.modelMapper.map(this.categoryRepository.findByName(name).orElseThrow(), CategoryModel.class);
    }
}
