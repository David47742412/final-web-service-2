package com.app.app.finalproject.service.review;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.review.ReviewEntity;
import com.app.app.finalproject.interfaces.main.IModel;
import com.app.app.finalproject.repositories.review.IReviewImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements IModel<ReviewEntity> {

    private final IReviewImpl _repository;

    public ReviewService(IReviewImpl repository) {
        this._repository = repository;
    }

    @Override
    public boolean exists(String id) {
        return this._repository.existsById(id);
    }

    @Override
    public ResponseEntity<ResponseDto<ReviewEntity>> find() {
        var res = new ResponseDto<ReviewEntity>();
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
    public ResponseEntity<ResponseDto<ReviewEntity>> insert(ReviewEntity entity) {
        entity.reviewId = null;
        var res = new ResponseDto<ReviewEntity>();
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
    public ResponseEntity<ResponseDto<ReviewEntity>> update(String id, ReviewEntity entity) {
        entity.reviewId = id;
        var res = new ResponseDto<ReviewEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Review not found");
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
    public ResponseEntity<ResponseDto<ReviewEntity>> delete(String id) {
        var res = new ResponseDto<ReviewEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Review not found");
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