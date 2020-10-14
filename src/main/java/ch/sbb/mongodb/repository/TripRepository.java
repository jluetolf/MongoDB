package ch.sbb.mongodb.repository;

import ch.sbb.mongodb.objects.Customer;
import ch.sbb.mongodb.objects.Trip;
import ch.sbb.mongodb.objects.Trips;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TripRepository extends MongoRepository<Trip, Long> {
}
