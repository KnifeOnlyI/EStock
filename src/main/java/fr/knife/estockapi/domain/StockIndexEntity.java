package fr.knife.estockapi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * Represent a stock index
 */
@Entity
@Table(name = "stock_index")
@Getter
@Setter
@Accessors(chain = true)
public class StockIndexEntity {
    /**
     * The ID
     */
    @Id
    @SequenceGenerator(name = "stock_index_id_gen", sequenceName = "stock_index_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "stock_index_id_gen")
    private Long id;

    private String name;

    /**
     * The stock list
     */
    @ManyToMany
    @JoinTable(
        name = "tracked_stock_index",
        joinColumns = @JoinColumn(name = "stock_index_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "tracked_stock_id", referencedColumnName = "id")
    )
    private List<TrackedStockEntity> stocks = new java.util.ArrayList<>();
}
