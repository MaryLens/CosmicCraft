package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Image;

public interface ImageDecorator {
    Image getImage();
    String getBase64();
}
