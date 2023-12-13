package com.app.app.finalproject.controllers.author;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.author.AuthorEntity;
import com.app.app.finalproject.service.author.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService _service;

    public AuthorController(AuthorService service) {
        _service = service;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<AuthorEntity>> find() {
        return this._service.find();
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<AuthorEntity>> insert(@RequestBody() AuthorEntity entity) {
        return this._service.insert(entity);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto<AuthorEntity>> update(@PathVariable("id") String id, @RequestBody() AuthorEntity entity) {
        return this._service.update(id, entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto<AuthorEntity>> delete(@PathVariable("id") String id) {
        return this._service.delete(id);
    }
}
