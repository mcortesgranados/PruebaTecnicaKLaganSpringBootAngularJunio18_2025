package com.mcg.klagan.pruebatecnica.adapter.out.persistence;

import com.mcg.klagan.pruebatecnica.adapter.out.persistence.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SpringWarehouseRepository is a Spring Data JPA repository interface
 * that provides persistence operations for {@link WarehouseEntity}.
 *
 * 🧱 Role in Hexagonal Architecture:
 *      - This interface is part of the outbound adapter layer (secondary port implementation).
 *      - Acts as the technical infrastructure for accessing the persistence mechanism (H2, PostgreSQL, etc.).
 *      - Implements the domain port for warehouse persistence (typically defined in `application.port.out`).
 *
 * 🧼 Clean Architecture:
 *      - Located in the outermost layer (infrastructure).
 *      - Implements the output port for storage, but remains decoupled from business rules.
 *      - Used by a service or persistence adapter (e.g., `WarehousePersistenceAdapter`) to abstract away database logic.
 *
 * 📐 Domain-Driven Design (DDD):
 *      - Handles persistence of a domain aggregate root: `Warehouse`.
 *      - This interface should not be exposed to the domain layer directly.
 *      - A mapping (via a mapper) must convert between `WarehouseEntity` and the domain model `Warehouse`.
 *
 * 📐 SOLID Principles:
 * ------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *      - Has one responsibility: provide basic CRUD operations for warehouse persistence.
 *      - Does not contain any business logic or mapping rules.
 *
 * ✅ OCP (Open/Closed Principle):
 *      - Can be extended via query methods or Spring @Query annotations without changing existing methods.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *      - Can be safely used anywhere a `JpaRepository<WarehouseEntity, Long>` is expected.
 *
 * ❌ ISP (Interface Segregation Principle): Not applicable to Spring Data repositories directly.
 * ❌ DIP (Dependency Inversion Principle): Inversion is handled via dependency injection — services depend on this interface, not an implementation.
 *
 * 📌 Spring Boot:
 *      - Automatically implemented at runtime by Spring Data JPA.
 *      - Integrated via `@Repository` and managed as a Spring Bean.
 *
 * Example usage:
 *   - Called from an adapter class like `WarehousePersistenceAdapter` that implements an output port.
 *
 * Author: Manuela Cortés Granados
 * Since: 19 Junio 2025
 */
@Repository
public interface SpringWarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
    // Can be extended with custom query methods like:
    // List<WarehouseEntity> findByClient(String client);
}
