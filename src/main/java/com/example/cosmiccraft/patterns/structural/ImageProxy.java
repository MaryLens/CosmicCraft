package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.Image;
import com.example.cosmiccraft.repositories.ImageRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ImageProxy implements ImageDecorator {
    private final Long imageId;
    private final ImageRepository imageRepository;
    private Image image;

    @Override
    public Image getImage() {
        if (image == null) {
            image = imageRepository.findById(imageId)
                    .orElseThrow(() -> new RuntimeException("Image not found"));
        }
        return image;
    }

    @Override
    public String getBase64() {
        return getImage().getBase64();
    }
}
