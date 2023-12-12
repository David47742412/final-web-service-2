package com.app.app.finalproject.entity.category;

import jakarta.persistence.*;

@Entity(name = "category")
public class CategoryEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id", length = 36, nullable = false, updatable = false)
    public String categoryId;

    @Column(name = "name", length = 150, nullable = false)
    public String name;

    @Column(name = "description", length = 500, nullable = false)
    public String description;
}
