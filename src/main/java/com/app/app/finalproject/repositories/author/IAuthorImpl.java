package com.app.app.finalproject.repositories.author;

import com.app.app.finalproject.entity.author.AuthorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface IAuthorImpl extends CrudRepository<AuthorEntity, String> {

    @Query("SELECT a FROM author a WHERE a.phone = :phone")
    AuthorEntity findByPhone(String phone);

}
