package com.app.app.finalproject.service.rating;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.rating.RatingEntity;
import com.app.app.finalproject.interfaces.main.IModel;
import com.app.app.finalproject.repositories.rating.IRatingImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RatingService implements IModel<RatingEntity> {

    private final IRatingImpl _repository;

    public RatingService(IRatingImpl repository) {
        this._repository = repository;
    }

    @Override
    public boolean exists(String id) {
        return this._repository.existsById(id);
    }

    @Override
    public ResponseEntity<ResponseDto<RatingEntity>> find() {
        var res = new ResponseDto<RatingEntity>();
        try {
            res.body = this._repository.findAll();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while getting data, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<RatingEntity>> insert(RatingEntity entity) {
        entity.ratingId = null;
        var res = new ResponseDto<RatingEntity>();
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
    public ResponseEntity<ResponseDto<RatingEntity>> update(String id, RatingEntity entity) {
        entity.ratingId = id;
        var res = new ResponseDto<RatingEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Rating not found");
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
    public ResponseEntity<ResponseDto<RatingEntity>> delete(String id) {
        var res = new ResponseDto<RatingEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Rating not found");
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
