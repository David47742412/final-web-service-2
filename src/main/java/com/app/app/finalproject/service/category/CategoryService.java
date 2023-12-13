package com.app.app.finalproject.service.category;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.category.CategoryEntity;
import com.app.app.finalproject.interfaces.main.IModel;
import com.app.app.finalproject.repositories.category.ICategoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public ResponseEntity<ResponseDto<CategoryEntity>> find() {
        var res = new ResponseDto<CategoryEntity>();
        try {
            res.body = this._repository.findAll();
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Exception in find category: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<CategoryEntity>> insert(CategoryEntity entity) {
        entity.categoryId = null;
        var res = new ResponseDto<CategoryEntity>();
        try {
            this._repository.save(entity);
            res.body = this._repository.findAll();
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Exception in insert category: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<CategoryEntity>> update(String id, CategoryEntity entity) {
        var res = new ResponseDto<CategoryEntity>();
        try {
            var category = this._repository.findById(id);
            if (category.isEmpty()) {
                res.statusCode = 404;
                res.message.add("Category not found");
                return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
            }
            entity.categoryId = id;
            this._repository.save(entity);
            res.statusCode = 200;
            res.body = this._repository.findAll();
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Exception in update category: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<CategoryEntity>> delete(String id) {
        var res = new ResponseDto<CategoryEntity>();
        try {
            var category = this._repository.findById(id);
            if (category.isEmpty()) {
                res.statusCode = 404;
                res.message.add("Category not found");
                return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
            }
            this._repository.deleteById(id);
            res.statusCode = 200;
            res.body = this._repository.findAll();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Exception in delete category: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
