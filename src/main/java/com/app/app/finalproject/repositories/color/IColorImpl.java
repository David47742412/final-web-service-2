package com.app.app.finalproject.repositories.color;

import com.app.app.finalproject.entity.color.ColorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IColorImpl extends CrudRepository<ColorEntity, String> {
}
