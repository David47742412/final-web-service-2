package com.app.app.finalproject.controllers.category;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.category.CategoryEntity;
import com.app.app.finalproject.service.category.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService _service;

    public CategoryController(CategoryService service) {
        this._service = service;
    }

    @GetMapping()
    ResponseEntity<ResponseDto<CategoryEntity>> find() {
        return this._service.find();
    }

    @PostMapping()
    ResponseEntity<ResponseDto<CategoryEntity>> save(@RequestBody() CategoryEntity category) {
        return this._service.insert(category);
    }

    @PutMapping("{id}")
    ResponseEntity<ResponseDto<CategoryEntity>> update(@PathVariable("id") String id, @RequestBody() CategoryEntity category) {
        return this._service.update(id, category);
    }

    @DeleteMapping("{id}")
    ResponseEntity<ResponseDto<CategoryEntity>> delete(@PathVariable("id") String id) {
        return this._service.delete(id);
    }
}
