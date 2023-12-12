package com.app.app.finalproject.repositories.category;

import com.app.app.finalproject.entity.category.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryImpl extends CrudRepository<CategoryEntity, String> {
}
