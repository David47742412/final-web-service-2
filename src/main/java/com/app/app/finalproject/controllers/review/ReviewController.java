package com.app.app.finalproject.controllers.review;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.publisher.PublisherEntity;
import com.app.app.finalproject.entity.review.ReviewEntity;
import com.app.app.finalproject.service.review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService _service;

    public ReviewController(ReviewService service) {
        this._service = service;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<ReviewEntity>> find() {
        return this._service.find();
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<ReviewEntity>> insert(@RequestBody() ReviewEntity entity) {
        return this._service.insert(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ReviewEntity>> update(@PathVariable("id") String id, @RequestBody() ReviewEntity entity) {
        return this._service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<ReviewEntity>> delete(@PathVariable("id") String id) {
        return this._service.delete(id);
    }
}
