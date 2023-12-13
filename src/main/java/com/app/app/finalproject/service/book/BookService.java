package com.app.app.finalproject.service.book;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.book.BookEntity;
import com.app.app.finalproject.interfaces.main.IModel;
import com.app.app.finalproject.repositories.book.IBookImpl;
import com.app.app.finalproject.service.author.AuthorService;
import com.app.app.finalproject.service.category.CategoryService;
import com.app.app.finalproject.service.color.ColorService;
import com.app.app.finalproject.service.publisher.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IModel<BookEntity> {

    private final IBookImpl _repository;
    private final CategoryService _categoryService;
    private final AuthorService _authorService;
    private final ColorService _colorService;
    private final PublisherService _publisherService;

    public BookService(IBookImpl repository, CategoryService categoryService, AuthorService authorService, ColorService colorService, PublisherService publisherService) {
        this._repository = repository;
        _categoryService = categoryService;
        _authorService = authorService;
        _colorService = colorService;
        _publisherService = publisherService;
    }

    @Override
    public boolean exists(String id) {
        return this._repository.existsById(id);
    }

    @Override
    public ResponseEntity<ResponseDto<BookEntity>> find() {
        var res = new ResponseDto<BookEntity>();
        try {
            res.body = this._repository.findAll();
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while getting data, Error: " + ex.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<BookEntity>> insert(BookEntity entity) {
        entity.bookId = null;
        var res = new ResponseDto<BookEntity>();
        try {
            this.validations(entity, res);
            if (!res.message.isEmpty()) {
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            this._repository.save(entity);

            return this.find();

        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while inserting data, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<BookEntity>> update(String id, BookEntity entity) {
        entity.bookId = id;
        var res = new ResponseDto<BookEntity>();
        try {
            this.validations(entity, res);

            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Book not found");
            }

            if (!res.message.isEmpty()) {
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            this._repository.save(entity);

            return this.find();

        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while inserting data, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<BookEntity>> delete(String id) {
        var res = new ResponseDto<BookEntity>();
        try {

            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Book not found");
            }

            if (!res.message.isEmpty()) {
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            this._repository.deleteById(id);

            return this.find();

        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while inserting data, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    void validations(BookEntity entity, ResponseDto<BookEntity> res) {
        if (!this._colorService.exists(entity.color.colorId)) {
            res.statusCode = 400;
            res.message.add("Color not found");
        }

        if (!this._categoryService.exists(entity.category.categoryId)) {
            res.statusCode = 400;
            res.message.add("Category not found");
        }

        if (!this._publisherService.exists(entity.publisher.publisherId)) {
            res.statusCode = 400;
            res.message.add("Publisher not found");
        }

        if (!this._authorService.exists(entity.author.authorId)) {
            res.statusCode = 400;
            res.message.add("Author not found");
        }
    }

}
