package com.app.app.finalproject.entity.color;

import jakarta.persistence.*;

@Entity(name = "color")
public class ColorEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "color_id", length = 36, nullable = false, unique = true)
    public String colorId;

    @Column(name = "name", length = 100, nullable = false, columnDefinition = "VARCHAR(100)")
    public String name;

    @Column(name = "description", length = 150, nullable = false, columnDefinition = "VARCHAR(150)")
    public String description;
}
