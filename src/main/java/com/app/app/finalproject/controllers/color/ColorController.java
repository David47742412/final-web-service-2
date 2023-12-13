package com.app.app.finalproject.controllers.color;

import com.app.app.finalproject.dto.ResponseDto;
import com.app.app.finalproject.entity.color.ColorEntity;
import com.app.app.finalproject.service.color.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/color")
public class ColorController {

    private final ColorService _service;

    public ColorController(ColorService service) {
        this._service = service;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<ColorEntity>> find() {
        return this._service.find();
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<ColorEntity>> insert(@RequestBody() ColorEntity entity) {
        return this._service.insert(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ColorEntity>> update(@PathVariable("id") String id, @RequestBody() ColorEntity entity) {
        return this._service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<ColorEntity>> delete(@PathVariable("id") String id) {
        return this._service.delete(id);
    }
}
