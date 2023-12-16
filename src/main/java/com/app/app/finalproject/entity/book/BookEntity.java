package com.app.app.finalproject.entity.book;

import com.app.app.finalproject.entity.author.AuthorEntity;
import com.app.app.finalproject.entity.category.CategoryEntity;
import com.app.app.finalproject.entity.color.ColorEntity;
import com.app.app.finalproject.entity.publisher.PublisherEntity;
import com.app.app.finalproject.entity.rating.RatingEntity;
import com.app.app.finalproject.entity.review.ReviewEntity;
import jakarta.persistence.*;

@Entity(name = "book")
public class BookEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "book_id", length = 36)
    public String bookId;

    @Column(name = "name", length = 100, nullable = false, columnDefinition = "VARCHAR(100)")
    public String name;

    @Column(name = "description", length = 500, nullable = false, columnDefinition = "VARCHAR(500)")
    public String description;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)")
    public Double price;

    @Column(name = "stock", nullable = false, columnDefinition = "INT(10)")
    public Integer stock;

    @ManyToOne(targetEntity = AuthorEntity.class)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    public AuthorEntity author;

    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    public CategoryEntity category;

    @ManyToOne(targetEntity = ColorEntity.class)
    @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    public ColorEntity color;

    @ManyToOne(targetEntity = PublisherEntity.class)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    public PublisherEntity publisher;

    @ManyToOne(targetEntity = RatingEntity.class)
    @JoinColumn(name = "rating_id", referencedColumnName = "rating_id")
    public RatingEntity rating;

    @ManyToOne(targetEntity = ReviewEntity.class)
    @JoinColumn(name = "review_id", referencedColumnName = "review_id")
    public ReviewEntity review;

}
