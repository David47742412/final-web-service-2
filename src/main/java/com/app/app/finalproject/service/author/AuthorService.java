package com.app.app.finalproject.service.author;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.author.AuthorEntity;
import com.app.app.finalproject.interfaces.main.IModel;
import com.app.app.finalproject.repositories.author.IAuthorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class AuthorService implements IModel<AuthorEntity> {

    private final IAuthorImpl _repository;

    public AuthorService(IAuthorImpl repository) {
        this._repository = repository;
    }

    @Override
    public boolean exists(String id) {
        return this._repository.existsById(id);
    }

    @Override
    public ResponseEntity<ResponseDto<AuthorEntity>> find() {
        var res = new ResponseDto<AuthorEntity>();
        try {
            res.body = this._repository.findAll();
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while trying to get authors, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<AuthorEntity>> insert(AuthorEntity entity) {
        var res = new ResponseDto<AuthorEntity>();
        entity.phone = entity.phone.trim().toLowerCase();
        try {
            var existPhoneAuthor = this.findByPhone(entity.phone);
            if (existPhoneAuthor != null && existPhoneAuthor.phone.equals(entity.phone)) {
                res.statusCode = 400;
                res.message.add("This phone already exists");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            this._repository.save(entity);
            res.body = this._repository.findAll();
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while trying to insert author, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<AuthorEntity>> update(String id, AuthorEntity entity) {
        var res = new ResponseDto<AuthorEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("This author does not exists");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            var existPhoneAuthor = this.findByPhone(entity.phone);

            if (existPhoneAuthor != null && !existPhoneAuthor.authorId.equals(id)) {
                res.statusCode = 400;
                res.message.add("This phone already exists in another author");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            res.body = this._repository.findAll();

            return ResponseEntity.ok(res);

        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while trying to update author, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<AuthorEntity>> delete(String id) {
        var res = new ResponseDto<AuthorEntity>();
        try {
            if (!this.exists(id)) {
                res.statusCode = 400;
                res.message.add("This author does not exists");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            this._repository.deleteById(id);
            res.body = this._repository.findAll();
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            res.statusCode = 500;
            res.message.add("Error while trying to delete author, Error: " + ex.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public AuthorEntity findByPhone(String phone) {
        return this._repository.findByPhone(phone);
    }
}
