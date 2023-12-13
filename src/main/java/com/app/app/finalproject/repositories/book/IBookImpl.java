package com.app.app.finalproject.repositories.book;

import com.app.app.finalproject.entity.book.BookEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IBookImpl extends CrudRepository<BookEntity, String> {
}
