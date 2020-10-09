package ch.sbb.postgres.repository;

import ch.sbb.postgres.entities.TripTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripTableRepository extends JpaRepository<TripTable, Long> {

}
