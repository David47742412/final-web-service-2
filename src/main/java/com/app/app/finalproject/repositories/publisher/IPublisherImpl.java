package com.app.app.finalproject.repositories.publisher;

import com.app.app.finalproject.entity.publisher.PublisherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublisherImpl extends CrudRepository<PublisherEntity, String> {
}
