package fr.knife.estockapi.repository;

import fr.knife.estockapi.domain.TrackedStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository to manage tracked stock in the database
 */
@Repository
public interface TrackedStockRepository extends JpaRepository<TrackedStockEntity, Long> {
}
