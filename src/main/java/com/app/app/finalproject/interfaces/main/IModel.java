package com.app.app.finalproject.interfaces.main;

import com.app.app.finalproject.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface IModel<T> {
    boolean exists(String id);

    ResponseEntity<ResponseDto<T>> find();

    ResponseEntity<ResponseDto<T>> insert(T entity);

    ResponseEntity<ResponseDto<T>> update(String id, T entity);

    ResponseEntity<ResponseDto<T>> delete(String id);
}
