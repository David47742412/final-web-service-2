package com.app.app.finalproject.entity.review;

import jakarta.persistence.*;

@Entity(name = "review")
public class ReviewEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "review_id", length = 35, nullable = false, unique = true)
    public String reviewId;

    @Column(name = "reviewer", length = 50, nullable = false)
    public String reviewerName;

    @Column(name = "comment", length = 250, nullable = false)
    public String comment;
}
