package com.mcg.klagan.pruebatecnica.adapter.out.persistence.entity;

import com.mcg.klagan.pruebatecnica.domain.model.WarehouseFamily;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * WarehouseEntity represents the JPA entity that maps a warehouse to the "warehouses" table.
 *
 * 📦 Layer: Adapter → Outbound → Persistence
 * 🧱 Role in Hexagonal Architecture:
 *      - Acts as an infrastructure detail responsible for persistence.
 *      - Implements the output port requirement to store and retrieve `Warehouse` objects.
 *      - Keeps the domain model clean and free from persistence annotations.
 *
 * 📐 SOLID Principles Applied:
 * ------------------------------------------------------------------------------
 * ✅ Single Responsibility Principle (SRP):
 *      - Has a single responsibility: define the structure and mapping of a warehouse in the database.
 *      - It does not contain any business logic.
 *
 * ✅ Open/Closed Principle (OCP):
 *      - Can be extended with new fields or annotations without modifying the domain logic.
 *
 * ✅ Liskov Substitution Principle (LSP):
 *      - Used via interfaces and mapping (not inheritance here, but composition complies).
 *
 * ✅ Interface Segregation & Dependency Inversion (ISP/DIP):
 *      - Applied at the architectural level through use of `WarehouseRepositoryPort` and mappers.
 *
 * 🔄 Mapping Consideration:
 *      - Bidirectional relation with `ShelfEntity` for full access to shelves per warehouse.
 *      - Cascade and orphanRemoval ensures child records are managed with parent lifecycle.
 *
 * 💾 Persistence:
 *      - JPA annotations (`@Entity`, `@Id`, `@OneToMany`) handle table mappings.
 *      - Enum `WarehouseFamily` is stored as a string to keep database readability.
 *
 * 📌 Used with:
 *      - `WarehouseMapper` to convert between `WarehouseEntity` and domain `Warehouse`.
 *      - `SpringWarehouseRepository` (extends `JpaRepository`) for DB operations.
 *
 * Author: Manuela Cortés Granados
 * Since: 19 Junio 2025
 */
@Entity
@Table(name = "warehouses")
public class WarehouseEntity {

    /**
     * Primary key (auto-generated ID).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The client to which this warehouse belongs.
     */
    private String client;

    /**
     * The installation name (location or logical name).
     */
    private String installation;

    /**
     * Maximum number of shelves that can be installed in this warehouse.
     * Business logic uses this value to compute permutations.
     */
    private int maxShelves;

    /**
     * Family of the warehouse (EST or ROB), stored as a string in the DB.
     * The enum `WarehouseFamily` defines the valid types of shelf per family.
     */
    @Enumerated(EnumType.STRING)
    private WarehouseFamily family;

    /**
     * List of shelves associated with this warehouse.
     * - `mappedBy = "warehouse"`: owns the inverse side of the relation.
     * - `cascade = ALL`: persist and remove shelves with the warehouse.
     * - `orphanRemoval = true`: delete shelves if removed from the list.
     */
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShelfEntity> shelves = new ArrayList<>();

    // ------------------------------------------------------
    // Getters and Setters
    // Required by Spring Data JPA for data access
    // ------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getInstallation() {
        return installation;
    }

    public void setInstallation(String installation) {
        this.installation = installation;
    }

    public int getMaxShelves() {
        return maxShelves;
    }

    public void setMaxShelves(int maxShelves) {
        this.maxShelves = maxShelves;
    }

    public WarehouseFamily getFamily() {
        return family;
    }

    public void setFamily(WarehouseFamily family) {
        this.family = family;
    }

    public List<ShelfEntity> getShelves() {
        return shelves;
    }

    public void setShelves(List<ShelfEntity> shelves) {
        this.shelves = shelves;
    }
}
