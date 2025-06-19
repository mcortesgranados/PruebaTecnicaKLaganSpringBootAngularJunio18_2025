package com.mcg.klagan.pruebatecnica.domain.service;

import com.mcg.klagan.pruebatecnica.domain.model.ShelfType;
import com.mcg.klagan.pruebatecnica.domain.model.Warehouse;
import com.mcg.klagan.pruebatecnica.domain.model.WarehouseFamily;
import com.mcg.klagan.pruebatecnica.domain.port.input.WarehouseUseCase;
import com.mcg.klagan.pruebatecnica.domain.port.output.WarehouseRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * WarehouseService implements the core business logic for handling warehouses.
 *
 * 🧱 Hexagonal Architecture:
 * - This class represents the **application service** and implements the **input port** (WarehouseUseCase).
 * - It orchestrates domain model operations and delegates persistence to the **output port** (WarehouseRepositoryPort).
 *
 * 🧼 Clean Architecture:
 * - Part of the **application/use case layer**.
 * - Contains business rules and validation logic without being tied to frameworks or infrastructure.
 *
 * 📐 Domain-Driven Design (DDD):
 * - Implements business operations on the `Warehouse` aggregate.
 * - Encapsulates policies like shelf validation and family constraints.
 *
 * ✅ SOLID Principles:
 * ------------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Coordinates domain logic for warehouse creation, retrieval, deletion, and permutations.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Easily extendable for new use cases without modifying core logic.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *   - Fulfills the contract defined in the `WarehouseUseCase` interface.
 *
 * ✅ DIP (Dependency Inversion Principle):
 *   - Depends on the `WarehouseRepositoryPort` abstraction, not JPA or other infrastructure.
 *
 * 🧪 Testability:
 * - Fully testable by mocking `WarehouseRepositoryPort`.
 *
 * 📦 Layer: Domain → Application Service
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 4:53 AM -5 GMT Bogota DC Colombia
 */
@Service
public class WarehouseService implements WarehouseUseCase {

    private final WarehouseRepositoryPort warehouseRepository;

    public WarehouseService(WarehouseRepositoryPort warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    /**
     * Creates a warehouse after validating shelf types and capacity constraints.
     * @param warehouse domain object to persist
     * @return the saved warehouse
     */
    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        // Validate: only allowed shelf types for given warehouse family
        List<ShelfType> allowed = allowedShelfTypesByFamily(warehouse.getFamily());

        warehouse.getShelves().forEach(shelf -> {
            if (!allowed.contains(shelf.getType())) {
                throw new IllegalArgumentException("Shelf type not allowed for family: " + shelf.getType());
            }
        });

        // Validate: number of shelves must not exceed max
        if (warehouse.getShelves().size() > warehouse.getMaxShelves()) {
            throw new IllegalArgumentException("Number of shelves exceeds allowed maximum.");
        }

        return warehouseRepository.save(warehouse);
    }

    /**
     * Fetches a warehouse by its ID.
     * @param id warehouse ID
     * @return Optional with the warehouse if found
     */
    @Override
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    /**
     * Retrieves all warehouses from the system.
     * @return list of warehouses
     */
    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    /**
     * Deletes a warehouse by ID, only if it exists.
     * @param id the warehouse ID to delete
     */
    @Override
    public void deleteWarehouse(Long id) {
        if (!warehouseRepository.existsById(id)) {
            throw new NoSuchElementException("Warehouse not found with id: " + id);
        }
        warehouseRepository.deleteById(id);
    }

    /**
     * Calculates all possible permutations of shelf types for a warehouse,
     * based on its allowed types and max number of shelves.
     * @param warehouseId warehouse ID
     * @return list of valid permutations (e.g., ["AAA", "AAC", "ABA", ...])
     */
    @Override
    public List<String> calculatePermutations(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new NoSuchElementException("Warehouse not found"));

        List<ShelfType> allowed = allowedShelfTypesByFamily(warehouse.getFamily());
        int max = warehouse.getMaxShelves();

        return generatePermutations(allowed, max);
    }

    /**
     * Helper method: retrieves allowed shelf types per warehouse family.
     * @param family warehouse family (EST or ROB)
     * @return list of allowed shelf types
     */
    private List<ShelfType> allowedShelfTypesByFamily(WarehouseFamily family) {
        return switch (family) {
            case EST -> List.of(ShelfType.A, ShelfType.B, ShelfType.C);
            case ROB -> List.of(ShelfType.A, ShelfType.C, ShelfType.D);
        };
    }

    /**
     * Helper method: generates all permutations of given types with given length.
     * @param types shelf types allowed
     * @param length number of shelves
     * @return list of string permutations
     */
    private List<String> generatePermutations(List<ShelfType> types, int length) {
        List<String> result = new ArrayList<>();
        generatePermutationsRecursive("", types, length, result);
        return result;
    }

    /**
     * Recursive permutation generator.
     */
    private void generatePermutationsRecursive(String current, List<ShelfType> types, int remaining, List<String> result) {
        if (remaining == 0) {
            result.add(current);
        } else {
            for (ShelfType type : types) {
                generatePermutationsRecursive(current + type.name(), types, remaining - 1, result);
            }
        }
    }
}
