package com.app.app.finalproject.controllers.publisher;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.publisher.PublisherEntity;
import com.app.app.finalproject.service.publisher.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService _service;

    public PublisherController(PublisherService service) {
        this._service = service;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<PublisherEntity>> find() {
        return this._service.find();
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<PublisherEntity>> insert(@RequestBody() PublisherEntity entity) {
        return this._service.insert(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PublisherEntity>> update(@PathVariable("id") String id, @RequestBody() PublisherEntity entity) {
        return this._service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<PublisherEntity>> delete(@PathVariable("id") String id) {
        return this._service.delete(id);
    }
}
