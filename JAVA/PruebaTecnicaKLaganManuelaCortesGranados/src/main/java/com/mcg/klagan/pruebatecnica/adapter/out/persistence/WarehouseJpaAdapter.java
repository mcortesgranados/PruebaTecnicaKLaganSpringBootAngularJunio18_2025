package com.mcg.klagan.pruebatecnica.adapter.out.persistence;

import com.mcg.klagan.pruebatecnica.adapter.out.persistence.mapper.WarehouseMapper;
import com.mcg.klagan.pruebatecnica.domain.model.Warehouse;
import com.mcg.klagan.pruebatecnica.domain.port.output.WarehouseRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * WarehouseJpaAdapter is the outbound adapter that implements the output port interface
 * {@link WarehouseRepositoryPort}, acting as a bridge between the domain layer and the
 * persistence mechanism (JPA via Spring Data).
 *
 * 🧱 Hexagonal Architecture (Ports & Adapters):
 * - This class belongs to the **Outbound Adapter** layer.
 * - Implements the output port used by the domain layer.
 * - Delegates persistence to JPA and maps entities to domain models.
 *
 * 🧼 Clean Architecture:
 * - Keeps domain models free of persistence concerns.
 * - Pushes technical infrastructure details to the edges of the system.
 *
 * 📐 Domain-Driven Design (DDD):
 * - Implements the repository pattern for the `Warehouse` aggregate root.
 * - Responsible for persisting and retrieving domain models.
 * - Works alongside the `WarehouseMapper` to enforce the domain boundary.
 *
 * ✅ SOLID Principles:
 * ------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - This class is solely responsible for persistence operations.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Can be extended (e.g., with logging, caching) without modifying its interface.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *   - Fully respects the contract of the `WarehouseRepositoryPort`.
 *
 * ✅ ISP (Interface Segregation Principle):
 *   - Implements a small, focused interface with only needed methods.
 *
 * ✅ DIP (Dependency Inversion Principle):
 *   - The domain layer depends only on abstractions (the port), not this adapter.
 *
 * 🧪 Testability:
 *   - Easily testable via mocks for `SpringWarehouseRepository` and `WarehouseMapper`.
 *
 * 📦 Layer: Adapter → Outbound → Persistence
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 4:44 AM GMT -5 Bogota DC Colombia
 */
@Component
public class WarehouseJpaAdapter implements WarehouseRepositoryPort {

    private final SpringWarehouseRepository jpaRepository;
    private final WarehouseMapper mapper;

    /**
     * Constructor with dependency injection.
     * @param jpaRepository JPA repository for persistence
     * @param mapper Mapper for entity ↔ domain model conversion
     */
    public WarehouseJpaAdapter(SpringWarehouseRepository jpaRepository, WarehouseMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    /**
     * Finds a warehouse by its ID and maps it to the domain model.
     * @param id the warehouse ID
     * @return Optional containing the domain model if found
     */
    @Override
    public Optional<Warehouse> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    /**
     * Saves a domain warehouse (create or update).
     * Converts domain to entity and back to domain after persistence.
     * @param warehouse the domain warehouse
     * @return the saved domain warehouse
     */
    @Override
    public Warehouse save(Warehouse warehouse) {
        return mapper.toDomain(
                jpaRepository.save(mapper.toEntity(warehouse))
        );
    }

    /**
     * Returns all warehouses from the database.
     * @return list of domain warehouses
     */
    @Override
    public List<Warehouse> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a warehouse by its ID.
     * @param id warehouse ID
     */
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    /**
     * Checks if a warehouse exists by its ID.
     * @param id warehouse ID
     * @return true if exists, false otherwise
     */
    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
