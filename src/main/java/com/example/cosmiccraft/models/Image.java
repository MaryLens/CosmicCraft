package com.example.cosmiccraft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "originalFileName")
    private String originalFileName;

    @Lob
    private byte[] bytes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Product product;

    public String getBase64() {
        return Base64.getEncoder().encodeToString(this.bytes);
    }
}
