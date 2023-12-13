package com.app.app.finalproject.controllers.book;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.book.BookEntity;
import com.app.app.finalproject.service.book.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService _service;

    public BookController(BookService service) {
        this._service = service;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<BookEntity>> find() {
        return this._service.find();
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<BookEntity>> insert(@RequestBody() BookEntity entity) {
        return this._service.insert(entity);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto<BookEntity>> update(@PathVariable("id") String id, @RequestBody() BookEntity entity) {
        return this._service.update(id, entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto<BookEntity>> delete(@PathVariable("id") String id) {
        return this._service.delete(id);
    }
}
