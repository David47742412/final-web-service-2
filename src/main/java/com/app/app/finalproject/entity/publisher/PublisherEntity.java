package com.app.app.finalproject.entity.publisher;

import jakarta.persistence.*;

@Entity(name = "publisher")
public class PublisherEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "publisher_id", length = 36, nullable = false, unique = true)
    public String publisherId;

    @Column(name = "name", length = 100, nullable = false, columnDefinition = "VARCHAR(100)")
    public String name;

    @Column(name = "description", length = 150, nullable = false, columnDefinition = "VARCHAR(150)")
    public String description;

    @Column(name = "address", length = 150, nullable = false, columnDefinition = "VARCHAR(150)")
    public String address;
}