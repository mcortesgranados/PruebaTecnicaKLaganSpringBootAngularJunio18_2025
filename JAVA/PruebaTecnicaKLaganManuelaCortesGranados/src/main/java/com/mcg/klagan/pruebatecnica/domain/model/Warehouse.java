package com.mcg.klagan.pruebatecnica.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Warehouse represents the aggregate root in the domain layer for warehouse management.
 *
 * 🧱 Hexagonal Architecture:
 *   - This class is located in the **domain model**, the innermost and most stable part of the system.
 *   - It encapsulates business rules and invariants, and is isolated from any infrastructure concerns.
 *   - It is manipulated exclusively via application services or use cases.
 *
 * 🧼 Clean Architecture:
 *   - This is a pure domain entity — it contains no annotations (like @Entity) or technical dependencies.
 *   - It is never directly exposed to the web layer (adapter) or persistence (JPA). Instead, adapters map to/from it.
 *   - Promotes complete independence from frameworks (Spring, JPA, etc.).
 *
 * 📐 Domain-Driven Design (DDD):
 *   - This is the **aggregate root** in the bounded context of warehouse management.
 *   - It enforces consistency boundaries: a `Warehouse` manages its internal collection of `Shelf` objects.
 *   - Business logic (such as max shelf validation) should be implemented here or via domain services.
 *
 * 📐 SOLID Principles:
 * ------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Represents a warehouse in the business domain, encapsulating only business-relevant data.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Can be extended (e.g., validations, policies) without modifying existing logic.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *   - As a concrete domain class, behaves correctly when used in polymorphic contexts.
 *
 * ✅ ISP / DIP: Not directly applicable to this entity, but respected in how it is used by ports and services.
 *
 * 🔄 Mapping Strategy:
 *   - This class is mapped from/to `WarehouseEntity` in the persistence layer via a mapper class.
 *   - Also mapped to/from `WarehouseRequestDto` and `WarehouseResponseDto` in the web layer.
 *
 * 🚫 No annotations like @Entity, @Table, etc. — ensures domain purity and testability.
 *
 * Author: Manuela Cortés Granados
 * Since: 19 Junio 2025 4:26 AM -5 GMT Bogota DC Colombia
 */
public class Warehouse {

    /**
     * Domain identifier (not tied to JPA).
     */
    private Long id;

    /**
     * Client who owns this warehouse.
     */
    private String client;

    /**
     * Installation where this warehouse is located.
     */
    private String installation;

    /**
     * Warehouse family classification (EST or ROB), determines allowed shelf types.
     */
    private WarehouseFamily family;

    /**
     * Maximum number of shelves allowed in the warehouse.
     */
    private int maxShelves;

    /**
     * List of shelves currently installed in the warehouse.
     * This is part of the aggregate and must be managed through domain logic.
     */
    private List<Shelf> shelves = new ArrayList<>();

    // --- Getters y Setters (Generated or managed by service layer) ---

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

    public WarehouseFamily getFamily() {
        return family;
    }

    public void setFamily(WarehouseFamily family) {
        this.family = family;
    }

    public int getMaxShelves() {
        return maxShelves;
    }

    public void setMaxShelves(int maxShelves) {
        this.maxShelves = maxShelves;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    // --- Equals & HashCode based on ID only (used for testing, sets, maps) ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;
        Warehouse that = (Warehouse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
