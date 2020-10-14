package ch.sbb.mongodb.repository;

import ch.sbb.mongodb.objects.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TripRepository extends MongoRepository<Trip, Long> {
}
