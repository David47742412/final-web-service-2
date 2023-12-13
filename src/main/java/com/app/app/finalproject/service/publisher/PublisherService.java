package com.app.app.finalproject.service.publisher;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.publisher.PublisherEntity;
import com.app.app.finalproject.interfaces.main.IModel;
import com.app.app.finalproject.repositories.publisher.IPublisherImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublisherService implements IModel<PublisherEntity> {

    private final IPublisherImpl _repository;

    public PublisherService(IPublisherImpl repository) {
        this._repository = repository;
    }

    @Override
    public boolean exists(String id) {
        return this._repository.existsById(id);
    }

    @Override
    public ResponseEntity<ResponseDto<PublisherEntity>> find() {
        var res = new ResponseDto<PublisherEntity>();
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
    public ResponseEntity<ResponseDto<PublisherEntity>> insert(PublisherEntity entity) {
        entity.publisherId = null;
        var res = new ResponseDto<PublisherEntity>();
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
    public ResponseEntity<ResponseDto<PublisherEntity>> update(String id, PublisherEntity entity) {
        entity.publisherId = id;
        var res = new ResponseDto<PublisherEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Publisher not found");
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
    public ResponseEntity<ResponseDto<PublisherEntity>> delete(String id) {
        var res = new ResponseDto<PublisherEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("Publisher not found");
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
