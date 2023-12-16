package com.app.app.finalproject.repositories.review;

import com.app.app.finalproject.entity.review.ReviewEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IReviewImpl extends CrudRepository<ReviewEntity, String> {
}
