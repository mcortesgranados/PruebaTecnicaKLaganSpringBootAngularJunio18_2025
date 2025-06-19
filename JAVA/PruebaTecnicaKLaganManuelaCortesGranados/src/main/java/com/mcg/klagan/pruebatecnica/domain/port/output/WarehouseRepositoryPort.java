package com.mcg.klagan.pruebatecnica.domain.port.output;

import com.mcg.klagan.pruebatecnica.domain.model.Warehouse;

import java.util.Optional;

/**
 * WarehouseRepositoryPort is a domain-driven output port interface that defines
 * the contract for persistence operations related to the {@link Warehouse} aggregate.
 *
 * 🧱 Hexagonal Architecture:
 *   - This interface defines a secondary port (output port).
 *   - Represents the communication boundary between the domain/application core and infrastructure.
 *   - Is implemented by an outbound adapter (e.g., `WarehousePersistenceAdapter`), which delegates
 *     to Spring Data JPA or any other persistence technology.
 *
 * 🧼 Clean Architecture:
 *   - This is a boundary interface in the application core (domain).
 *   - It allows the business logic to remain independent of any persistence technology.
 *   - Application services depend on this interface instead of concrete repositories.
 *
 * 📐 Domain-Driven Design (DDD):
 *   - Encapsulates operations for storing and retrieving the `Warehouse` aggregate.
 *   - Keeps the domain model pure by depending only on interfaces.
 *   - Allows for mocking or swapping implementations in testing or production environments.
 *
 * 📐 SOLID Principles:
 * ------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Has one responsibility: declare persistence operations for the Warehouse aggregate.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Open to new implementations (e.g., JPA, MongoDB, REST clients) without modifying the interface.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *   - Any implementation of this interface can substitute another without breaking behavior.
 *
 * ✅ ISP (Interface Segregation Principle):
 *   - Focused on the specific operations needed by the domain (not bloated).
 *
 * ✅ DIP (Dependency Inversion Principle):
 *   - The domain/application core depends on this abstraction, not a concrete technology.
 *
 * 🔌 Implementation Note:
 *   - This port will typically be implemented by an adapter class such as `WarehousePersistenceAdapter`,
 *     which uses `SpringWarehouseRepository` to interact with the database.
 *
 * Example in application layer:
 *   warehouseService.findWarehouseById(id) → calls WarehouseRepositoryPort.findById(id)
 *
 * Author: Manuela Cortés Granados
 * Since: 19 Junio 2025
 */
public interface WarehouseRepositoryPort {

    /**
     * Retrieves a Warehouse aggregate by its unique identifier.
     * @param id the warehouse ID
     * @return an Optional containing the Warehouse if found
     */
    Optional<Warehouse> findById(Long id);

    /**
     * Saves or updates a Warehouse aggregate in the persistence layer.
     * @param warehouse the warehouse to persist
     * @return the saved warehouse
     */
    Warehouse save(Warehouse warehouse);
}
