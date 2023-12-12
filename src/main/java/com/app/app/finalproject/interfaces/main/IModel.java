package com.app.app.finalproject.interfaces.main;

import org.springframework.http.ResponseEntity;

public interface IModel<T> {
    boolean exists(String id);

    ResponseEntity<Iterable<T>> find();

    ResponseEntity<Iterable<T>> insert(T entity);

    ResponseEntity<Iterable<T>> update(String id, T entity);

    ResponseEntity<Iterable<T>> delete(String id);
}
