package com.mcg.klagan.pruebatecnica.adapter.out.persistence.entity;

import com.mcg.klagan.pruebatecnica.domain.model.ShelfType;
import jakarta.persistence.*;

/**
 * ShelfEntity represents the JPA-mapped database entity for a shelf.
 *
 * 📦 Layer: Adapter → Outbound → Persistence
 * 🧱 Role in Hexagonal Architecture:
 *      - This class is part of the infrastructure layer.
 *      - It serves as the bridge between the domain model (pure Java) and the relational database (H2).
 *      - It persists data from the domain model into the database without polluting the domain with framework concerns.
 *
 * 📐 SOLID Principles Applied:
 * ------------------------------------------------------------------------------
 * ✅ Single Responsibility Principle (SRP):
 *      - This class has only one reason to change: changes in the database mapping of a Shelf.
 *      - It does not contain business logic (which belongs in the domain layer).
 *
 * ✅ Open/Closed Principle (OCP):
 *      - Closed for modification: JPA mappings remain stable.
 *      - Open for extension: New mapping annotations or fields can be added without altering the core logic.
 *
 * ✅ Liskov Substitution Principle (LSP):
 *      - Respected by relying only on standard types (e.g., enums, Long, associations).
 *
 * ✅ Interface Segregation Principle (ISP) & Dependency Inversion Principle (DIP):
 *      - Not directly applicable at the entity level but respected overall by separating persistence from domain via mappers.
 *
 * @author Manuela Cortés Granados
 * @since 19 Junio 2025 3:34 AM
 */
@Entity
@Table(name = "shelves")
public class ShelfEntity {

    /**
     * Unique identifier for the shelf (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Shelf type (A, B, C, D), stored as string in DB.
     *
     * Uses domain enum ShelfType to maintain consistency between persistence and domain model.
     */
    @Enumerated(EnumType.STRING)
    private ShelfType type;

    /**
     * Many-to-one relationship with WarehouseEntity.
     *
     * This reflects that each shelf belongs to a single warehouse.
     * It enables the bidirectional mapping between shelves and their parent warehouse.
     */
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseEntity warehouse;

    // ------------------------------------------------------
    // Getters and setters to allow Spring Data JPA to access fields
    // ------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShelfType getType() {
        return type;
    }

    public void setType(ShelfType type) {
        this.type = type;
    }

    public WarehouseEntity getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseEntity warehouse) {
        this.warehouse = warehouse;
    }
}
