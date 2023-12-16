package com.app.app.finalproject.entity.rating;

import jakarta.persistence.*;

@Entity(name = "rating")
public class RatingEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rating_id", length = 36, nullable = false, unique = true)
    public String ratingId;

    @Column(name = "rating", length = 50, nullable = false)
    public int rating;
}
