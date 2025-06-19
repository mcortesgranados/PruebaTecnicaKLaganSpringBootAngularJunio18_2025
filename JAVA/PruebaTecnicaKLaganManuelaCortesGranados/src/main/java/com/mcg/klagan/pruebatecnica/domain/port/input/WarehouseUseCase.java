package com.mcg.klagan.pruebatecnica.domain.port.input;

import com.mcg.klagan.pruebatecnica.domain.model.Warehouse;

import java.util.List;
import java.util.Optional;

/**
 * WarehouseUseCase defines the application use cases related to the Warehouse aggregate.
 *
 * 🧱 Hexagonal Architecture (Ports & Adapters):
 * - This is an **input port**, which the domain layer exposes.
 * - Used by the **Application Services** or **Inbound Adapters** (e.g., REST Controllers).
 *
 * 🧼 Clean Architecture:
 * - Resides in the **application layer**.
 * - Keeps orchestration and business logic decoupled from infrastructure or delivery mechanisms.
 * - Represents application-level business rules.
 *
 * 📐 Domain-Driven Design (DDD):
 * - Represents the **use cases** for the `Warehouse` aggregate.
 * - Encourages explicit boundaries and contracts for interacting with domain logic.
 *
 * ✅ SOLID Principles:
 * -------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Interface dedicated to defining business use cases for Warehouses.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - New operations can be added through separate interfaces or extensions.
 *
 * ✅ ISP (Interface Segregation Principle):
 *   - Only includes methods relevant to Warehouse domain logic.
 *
 * ✅ DIP (Dependency Inversion Principle):
 *   - High-level modules (controllers) depend on this abstraction, not concrete classes.
 *
 * 🧪 Testability:
 * - Facilitates mocking and testability of application flow.
 *
 * 📦 Layer: Domain → Application (Use Cases)
 *
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 4:48 AM GMT -5 Bogota DC Colombia
 */
public interface WarehouseUseCase {

    /**
     * Creates and persists a new warehouse.
     * @param warehouse the warehouse aggregate to create
     * @return the created warehouse
     */
    Warehouse createWarehouse(Warehouse warehouse);

    /**
     * Retrieves a warehouse by its unique ID.
     * @param id the warehouse ID
     * @return an Optional containing the warehouse if found
     */
    Optional<Warehouse> getWarehouseById(Long id);

    /**
     * Calculates all valid shelf type permutations for a given warehouse,
     * based on its family and max allowed shelves.
     * @param warehouseId the ID of the warehouse
     * @return list of valid shelf type permutations (e.g., ["AAA", "AAC", ...])
     */
    List<String> calculatePermutations(Long warehouseId);

    /**
     * Retrieves all warehouses in the system.
     * @return a list of warehouse aggregates
     */
    List<Warehouse> getAllWarehouses();

    /**
     * Deletes a warehouse by its ID.
     * @param id the warehouse ID to delete
     */
    void deleteWarehouse(Long id);
}
