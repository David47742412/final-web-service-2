package com.app.app.finalproject.service.color;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.color.ColorEntity;
import com.app.app.finalproject.interfaces.main.IModel;
import com.app.app.finalproject.repositories.color.IColorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ColorService implements IModel<ColorEntity> {

    private final IColorImpl _repository;

    public ColorService(IColorImpl repository) {
        this._repository = repository;
    }

    @Override
    public boolean exists(String id) {
        return this._repository.existsById(id);
    }

    @Override
    public ResponseEntity<ResponseDto<ColorEntity>> find() {
        var res = new ResponseDto<ColorEntity>();
        try {
            res.body = this._repository.findAll();
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while getting data, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<ColorEntity>> insert(ColorEntity entity) {
        entity.colorId = null;
        var res = new ResponseDto<ColorEntity>();
        try {
            this._repository.save(entity);
            return this.find();
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while getting data, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<ColorEntity>> update(String id, ColorEntity entity) {
        entity.colorId = id;
        var res = new ResponseDto<ColorEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Color not found");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            this._repository.save(entity);
            return this.find();
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while getting data, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<ColorEntity>> delete(String id) {
        var res = new ResponseDto<ColorEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Color not found");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            this._repository.deleteById(id);
            return this.find();
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while getting data, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
