package fr.knife.estockapi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Represent a tracked stock
 */
@Entity
@Table(name = "tracked_stock")
@Getter
@Setter
@Accessors(chain = true)
public class TrackedStockEntity {
    /**
     * The ID
     */
    @Id
    @SequenceGenerator(name = "tracked_stock_id_gen", sequenceName = "tracked_stock_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tracked_stock_id_gen")
    private Long id;

    /**
     * The name
     */
    private String name;

    /**
     * The ISIN code
     */
    private String isin;
}
