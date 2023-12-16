package com.app.app.finalproject.controllers.rating;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.rating.RatingEntity;
import com.app.app.finalproject.service.rating.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
public class RatingController {
    private final RatingService _service;

    public RatingController(RatingService service) {
        this._service = service;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<RatingEntity>> find() {
        return this._service.find();
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<RatingEntity>> insert(@RequestBody() RatingEntity entity) {
        return this._service.insert(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<RatingEntity>> update(@PathVariable("id") String id, @RequestBody() RatingEntity entity) {
        return this._service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<RatingEntity>> delete(@PathVariable("id") String id) {
        return this._service.delete(id);
    }

}
