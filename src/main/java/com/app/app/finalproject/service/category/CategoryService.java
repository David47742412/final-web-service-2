package com.app.app.finalproject.service.category;

import com.app.app.finalproject.entity.category.CategoryEntity;
import com.app.app.finalproject.interfaces.main.IModel;
import com.app.app.finalproject.repositories.category.ICategoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements IModel<CategoryEntity> {
    private final ICategoryImpl _repository;

    public CategoryService(ICategoryImpl repository) {
        this._repository = repository;
    }

    @Override
    public boolean exists(String id) {
        return this._repository.existsById(id);
    }

    @Override
    public ResponseEntity<Iterable<CategoryEntity>> find() {
        try {
            var categories = this._repository.findAll();
            return ResponseEntity.ok(categories);
        } catch (Exception ex) {
            System.out.println("Exception in find category: " + ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Iterable<CategoryEntity>> insert(CategoryEntity entity) {
        try {
            this._repository.save(entity);
            var categories = this._repository.findAll();
            return ResponseEntity.ok(categories);
        } catch (Exception ex) {
            System.out.println("Exception in insert category: " + ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Iterable<CategoryEntity>> update(String id, CategoryEntity entity) {
        try {
            var category = this._repository.findById(id);
            if (category.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entity.categoryId = id;
            this._repository.save(entity);
            var categories = this._repository.findAll();
            return ResponseEntity.ok(categories);
        } catch (Exception ex) {
            System.out.println("Exception in update category: " + ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Iterable<CategoryEntity>> delete(String id) {
        try {
            var category = this._repository.findById(id);
            if (category.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            this._repository.deleteById(id);
            var categories = this._repository.findAll();
            return ResponseEntity.ok(categories);
        } catch (Exception ex) {
            System.out.println("Exception in delete category: " + ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
