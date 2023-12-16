package com.app.app.finalproject.repositories.rating;

import com.app.app.finalproject.entity.rating.RatingEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IRatingImpl extends CrudRepository<RatingEntity, String> {
}
