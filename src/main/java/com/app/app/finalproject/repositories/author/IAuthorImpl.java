package com.app.app.finalproject.repositories.author;

import com.app.app.finalproject.entity.author.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorImpl extends CrudRepository<AuthorEntity, String> {
}
