package com.app.app.finalproject.entity.author;

import jakarta.persistence.*;

@Entity(name = "author")
public class AuthorEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "author_id", length = 36, nullable = false, unique = true)
    public String authorId;

    @Column(name = "name", length = 100, nullable = false, columnDefinition = "VARCHAR(100)")
    public String name;

    @Column(name = "last_name", length = 100, nullable = false, columnDefinition = "VARCHAR(100)")
    public String lastName;

    @Column(name = "phone", length = 9, nullable = false, unique = true, columnDefinition = "VARCHAR(9)")
    public String phone;
}
