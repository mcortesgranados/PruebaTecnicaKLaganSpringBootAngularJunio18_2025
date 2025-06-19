package com.mcg.klagan.pruebatecnica.adapter.in.web;

import com.mcg.klagan.pruebatecnica.adapter.in.web.dto.WarehouseRequestDto;
import com.mcg.klagan.pruebatecnica.adapter.in.web.dto.WarehouseResponseDto;
import com.mcg.klagan.pruebatecnica.adapter.in.web.mapper.WarehouseDtoMapper;
import com.mcg.klagan.pruebatecnica.domain.model.Warehouse;
import com.mcg.klagan.pruebatecnica.domain.port.input.WarehouseUseCase;
import com.mcg.klagan.pruebatecnica.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WarehouseController is a REST controller that handles HTTP requests related to warehouses.
 *
 * 🧱 Hexagonal Architecture:
 * - This class is part of the **Inbound Adapter** (Web Layer) responsible for receiving HTTP requests.
 * - Communicates with the domain layer only through the **WarehouseUseCase port**.
 *
 * 🧼 Clean Architecture:
 * - This belongs to the outermost layer (Interface Adapters).
 * - It only coordinates actions and delegates business logic to the domain through use cases.
 * - No domain logic is written here, ensuring proper separation of concerns.
 *
 * 🧠 Domain-Driven Design (DDD):
 * - Exposes application functionality as endpoints, delegating complex business logic to the domain.
 * - Transforms domain entities into DTOs and vice versa using mappers.
 *
 * ✅ SOLID Principles:
 * ---------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Handles web-specific logic (routing, validation, responses).
 *
 * ✅ DIP (Dependency Inversion Principle):
 *   - Depends on the abstraction `WarehouseUseCase`, not the implementation.
 *
 * ✅ LSP/OCP:
 *   - Easily extended to add new endpoints without modifying existing ones.
 *
 * 📦 Layer: Adapter → Inbound → Web (Controller)
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 5:46 AM GMT -5 Bogota DC Colombia
 */
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseUseCase warehouseUseCase;
    private final WarehouseDtoMapper warehouseDtoMapper;

    public WarehouseController(WarehouseUseCase warehouseUseCase,
                               WarehouseDtoMapper warehouseDtoMapper) {
        this.warehouseUseCase = warehouseUseCase;
        this.warehouseDtoMapper = warehouseDtoMapper;
    }

    /**
     * Creates a new warehouse based on incoming JSON request.
     * Delegates to domain logic via WarehouseUseCase.
     */
    @PostMapping
    public ResponseEntity<WarehouseResponseDto> createWarehouse(
            @Valid @RequestBody WarehouseRequestDto requestDto) {

        Warehouse warehouse = warehouseDtoMapper.toDomain(requestDto);
        Warehouse saved = warehouseUseCase.createWarehouse(warehouse);
        return ResponseEntity.ok(warehouseDtoMapper.toDto(saved));
    }

    /**
     * Retrieves a warehouse by its ID. Throws a 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponseDto> getWarehouseById(@PathVariable Long id) {
        Warehouse warehouse = warehouseUseCase
                .getWarehouseById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id: " + id));
        return ResponseEntity.ok(warehouseDtoMapper.toDto(warehouse));
    }

    /**
     * Returns a list of all warehouses.
     */
    @GetMapping
    public ResponseEntity<List<WarehouseResponseDto>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseUseCase.getAllWarehouses();
        List<WarehouseResponseDto> response = warehouses.stream()
                .map(warehouseDtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing warehouse. Validates the request body and delegates to the domain.
     */
    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponseDto> updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseRequestDto requestDto) {

        Warehouse updatedWarehouse = warehouseDtoMapper.toDomain(requestDto);
        Warehouse saved = warehouseUseCase.updateWarehouse(id, updatedWarehouse);
        return ResponseEntity.ok(warehouseDtoMapper.toDto(saved));
    }

    /**
     * Deletes a warehouse by its ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseUseCase.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Calculates and returns all valid shelf type permutations for the given warehouse ID.
     */
    @GetMapping("/{id}/shelf-permutations")
    public ResponseEntity<List<String>> getShelfPermutations(@PathVariable Long id) {
        List<String> permutations = warehouseUseCase.calculatePermutations(id);
        return ResponseEntity.ok(permutations);
    }
}
