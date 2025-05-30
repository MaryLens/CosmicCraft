package com.example.cosmiccraft.services;

import com.example.cosmiccraft.models.Category;
import com.example.cosmiccraft.models.Image;
import com.example.cosmiccraft.models.Product;
import com.example.cosmiccraft.models.view.ProductView;
import com.example.cosmiccraft.patterns.structural.*;
import com.example.cosmiccraft.repositories.ImageRepository;
import com.example.cosmiccraft.repositories.LogRepository;
import com.example.cosmiccraft.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    private final LogRepository logRepository;
    private final DatabaseLoggingAdapter logger;


    public List<ProductView> getProducts(String title, Long categoryId, Integer maxPrice) {
        logger.info("Fetching products with title:"+title+", categoryId: "+categoryId+", maxPrice:" + maxPrice);
        List<Product> products = new ArrayList<>();
        try {
            products = productRepository.findAll();
            ProductFilter filter = new StandartProductFilter(new CombinedFilterImpl());
            products = filter.filterProducts(products, title, categoryId, maxPrice);
            logger.debug("Found {} products after filtering", products.size());
        } catch (Exception e) {
            logger.error("Error fetching products: {}", e.getMessage());
            throw e;
        }
        return products.stream()
                .map(this::toProductView)
                .collect(Collectors.toList());
    }

    public void saveProduct(Product product, MultipartFile[] files, Category category) {
        product.setCategory(category);
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Image image = fileToImage(file);
                image.setProduct(product);
                images.add(image);
            }
        }
        product.setImages(images);
        logger.debug("Product saved successfully: {}", product.getTitle());
        productRepository.save(product);
    }

    public void updateProduct(Product product, MultipartFile[] files, Category category) {
        product.setCategory(category);
        if (files != null) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    Image image = fileToImage(file);
                    image.setProduct(product);
                    images.add(image);
                }
            }
            product.setImages(images);
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductView getProductById(Long id) {
        return toProductView(productRepository.findById(id).orElse(null));
    }

    private Image fileToImage(MultipartFile file) {
        Image image = new Image();
        image.setOriginalFileName(file.getOriginalFilename());
        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    private ProductView toProductView(Product product) {
        ProductDecorator decorator = new BaseProductDecorator(product);
        if (product.isOnSale()) {
            decorator = new DiscountedProductDecorator(decorator, 0.2);
        }
        ProductView view = new ProductView();
        view.setId(product.getId());
        view.setTitle(product.getTitle());
        view.setDescription(decorator.getDescription());
        view.setPrice(decorator.getPrice());
        view.setCategory(product.getCategory());
        view.setOnSale(product.isOnSale());
        List<ImageDecorator> imageProxies = new ArrayList<>();
        for (Image image : product.getImages()) {
            imageProxies.add(new ImageProxy(image.getId(),imageRepository));
        }
        view.setImages(imageProxies);
        return view;
    }

    public void updateSaleStatus(Long productId, boolean isOnSale) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setOnSale(isOnSale);
        productRepository.save(product);
    }
}
