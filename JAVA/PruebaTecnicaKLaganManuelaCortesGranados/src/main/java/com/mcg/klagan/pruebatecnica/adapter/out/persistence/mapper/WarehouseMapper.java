package com.mcg.klagan.pruebatecnica.adapter.out.persistence.mapper;

import com.mcg.klagan.pruebatecnica.adapter.out.persistence.entity.ShelfEntity;
import com.mcg.klagan.pruebatecnica.adapter.out.persistence.entity.WarehouseEntity;
import com.mcg.klagan.pruebatecnica.domain.model.Shelf;
import com.mcg.klagan.pruebatecnica.domain.model.ShelfType;
import com.mcg.klagan.pruebatecnica.domain.model.Warehouse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WarehouseMapper acts as a translator between the persistence layer (JPA entities)
 * and the domain model, enabling infrastructure-agnostic domain logic.
 *
 * 🧱 Hexagonal Architecture:
 *   - This is part of the **Outbound Adapter layer** (adapter → out → persistence → mapper).
 *   - Connects the secondary port (WarehouseRepositoryPort) with the infrastructure layer.
 *   - Ensures that the domain model is never polluted with persistence annotations or logic.
 *
 * 🧼 Clean Architecture:
 *   - Facilitates the transition from `WarehouseEntity` (used by JPA) to `Warehouse` (domain).
 *   - Maintains strict separation of concerns: domain ↔ persistence.
 *   - Prevents leakage of JPA concerns into the domain.
 *
 * 📐 Domain-Driven Design (DDD):
 *   - Converts between Aggregate Root (`Warehouse`) and Entity (`WarehouseEntity`).
 *   - Also maps child entities (`Shelf`, `ShelfEntity`) respecting aggregation boundaries.
 *   - Ensures invariants and associations are kept intact (e.g., shelf belongs to warehouse).
 *
 * 📐 SOLID Principles:
 * ------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Responsible solely for mapping between domain and persistence representations.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Can be extended (e.g., map additional fields or handle nulls) without modifying core logic.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *   - Can be replaced with an alternative mapping strategy without breaking clients.
 *
 * ✅ ISP (Interface Segregation Principle):
 *   - This class doesn't implement an interface, but its internal methods are focused and cohesive.
 *
 * ✅ DIP (Dependency Inversion Principle):
 *   - This class depends on domain and entity classes, not on technical infrastructure.
 *
 * 🧪 Testability:
 *   - Can be tested independently with unit tests to validate conversion logic.
 *
 * Autor: Manuela Cortés Granados
 * Desde: 19 Junio 2025 -5 GMT Bogota DC Colombia
 */
@Component
public class WarehouseMapper {

    /**
     * Converts a persistence-level entity to a domain-level aggregate.
     * @param entity the warehouse JPA entity
     * @return domain model
     */
    public Warehouse toDomain(WarehouseEntity entity) {
        if (entity == null) return null;

        Warehouse warehouse = new Warehouse();
        warehouse.setId(entity.getId());
        warehouse.setClient(entity.getClient());
        warehouse.setInstallation(entity.getInstallation());
        warehouse.setFamily(entity.getFamily());
        warehouse.setMaxShelves(entity.getMaxShelves());

        if (entity.getShelves() != null) {
            warehouse.setShelves(
                    entity.getShelves().stream()
                            .map(this::toDomainShelf)
                            .collect(Collectors.toList())
            );
        }

        return warehouse;
    }

    /**
     * Converts a domain-level aggregate to a persistence-level JPA entity.
     * @param domain the domain aggregate
     * @return persistence entity
     */
    public WarehouseEntity toEntity(Warehouse domain) {
        if (domain == null) return null;

        WarehouseEntity entity = new WarehouseEntity();
        entity.setId(domain.getId());
        entity.setClient(domain.getClient());
        entity.setInstallation(domain.getInstallation());
        entity.setFamily(domain.getFamily());
        entity.setMaxShelves(domain.getMaxShelves());

        if (domain.getShelves() != null) {
            List<ShelfEntity> shelfEntities = domain.getShelves().stream()
                    .map(shelf -> {
                        ShelfEntity shelfEntity = toEntityShelf(shelf);
                        shelfEntity.setWarehouse(entity); // bidirectional mapping
                        return shelfEntity;
                    })
                    .collect(Collectors.toList());
            entity.setShelves(shelfEntities);
        }

        return entity;
    }

    /**
     * Converts a persistence-level shelf entity to a domain shelf.
     */
    private Shelf toDomainShelf(ShelfEntity entity) {
        Shelf shelf = new Shelf();
        shelf.setId(entity.getId());
        shelf.setType(entity.getType());
        shelf.setWarehouseId(
                entity.getWarehouse() != null ? entity.getWarehouse().getId() : null
        );
        return shelf;
    }

    /**
     * Converts a domain-level shelf to a persistence-level shelf entity.
     */
    private ShelfEntity toEntityShelf(Shelf shelf) {
        ShelfEntity entity = new ShelfEntity();
        entity.setId(shelf.getId());
        entity.setType(shelf.getType() != null ? shelf.getType() : ShelfType.A); // default fallback
        return entity;
    }
}
