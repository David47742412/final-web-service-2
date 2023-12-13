package com.app.app.finalproject.repositories.publisher;

import com.app.app.finalproject.entity.publisher.PublisherEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IPublisherImpl extends CrudRepository<PublisherEntity, String> {
}
