package com.example.cosmiccraft.models.view;

import com.example.cosmiccraft.models.Category;
import com.example.cosmiccraft.patterns.structural.ImageDecorator;
import lombok.Data;

import java.util.List;

@Data
public class ProductView {
    private Long id;
    private String title;
    private String description;
    private int price;
    private Category category;
    private List<ImageDecorator> images;
    private boolean onSale;
}
