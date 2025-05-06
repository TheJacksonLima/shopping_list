package com.jfl.shopping_list.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "items")
@Data
public class Item {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false, length = 100)
    private Integer category;

    @Column(name = "desc", nullable = false,length = 200)
    private String desc;

    @Column(name = "desc_short", nullable = false,length = 200)
    private String descShort;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image_url", nullable = false,length = 300)
    private String imageUrl;
}