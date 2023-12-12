package com.app.app.finalproject.controllers.category;

import com.app.app.finalproject.entity.category.CategoryEntity;
import com.app.app.finalproject.service.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService _service;

    public CategoryController(CategoryService service) {
        this._service = service;
    }

    @GetMapping()
    ResponseEntity<Iterable<CategoryEntity>> find() {
        return this._service.find();
    }
}
