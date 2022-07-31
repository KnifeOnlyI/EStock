package fr.knife.estockapi.repository;

import fr.knife.estockapi.domain.StockIndexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository to manage stock indexes
 */
@Repository
public interface StockIndexRepository extends JpaRepository<StockIndexEntity, Long> {
}
