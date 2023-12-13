package com.app.app.finalproject.repositories.category;

import com.app.app.finalproject.entity.category.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ICategoryImpl extends CrudRepository<CategoryEntity, String> {
}
