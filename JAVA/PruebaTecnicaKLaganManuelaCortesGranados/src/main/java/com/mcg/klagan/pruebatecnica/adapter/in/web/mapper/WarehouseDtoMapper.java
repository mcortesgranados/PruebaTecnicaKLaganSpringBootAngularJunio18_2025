package com.mcg.klagan.pruebatecnica.adapter.in.web.mapper;

import com.mcg.klagan.pruebatecnica.adapter.in.web.dto.ShelfRequestDto;
import com.mcg.klagan.pruebatecnica.adapter.in.web.dto.ShelfResponseDto;
import com.mcg.klagan.pruebatecnica.adapter.in.web.dto.WarehouseRequestDto;
import com.mcg.klagan.pruebatecnica.adapter.in.web.dto.WarehouseResponseDto;
import com.mcg.klagan.pruebatecnica.domain.model.Shelf;
import com.mcg.klagan.pruebatecnica.domain.model.Warehouse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WarehouseDtoMapper is responsible for transforming data between Web DTOs and Domain models.
 *
 * 🧱 Hexagonal Architecture:
 * - Belongs to the **Inbound Adapter** layer (Web → Domain bridge).
 * - Facilitates decoupling between external representations (DTOs) and internal domain models.
 *
 * 🧼 Clean Architecture:
 * - Acts within the **Interface Adapters** layer.
 * - Mediates transformations between incoming data and core domain logic.
 * - Prevents leakage of HTTP/web-specific concerns into the domain.
 *
 * 🧠 Domain-Driven Design (DDD):
 * - Respects the boundary between **DTOs** (external models) and **Entities/Aggregates** (domain).
 * - Preserves domain purity by isolating transformation logic here.
 *
 * ✅ SOLID Principles:
 * --------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - This class is solely responsible for transforming data between layers.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - New transformation rules (e.g., new fields) can be added without modifying the domain or web layers.
 *
 * ✅ DIP (Dependency Inversion Principle):
 *   - Relies only on high-level abstractions (DTOs and domain models), not on infrastructure.
 *
 * 📦 Layer: Adapter → Inbound → Web (Mapper)
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 5:39 AM GMT -5 Bogota DC Colombia
 */
@Component
public class WarehouseDtoMapper {

    /**
     * Converts a request DTO from the web layer into a domain Warehouse object.
     * This ensures that the domain layer works only with valid, mapped internal data.
     */
    public Warehouse toDomain(WarehouseRequestDto dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setClient(dto.getClient());
        warehouse.setInstallation(dto.getInstallation());
        warehouse.setFamily(dto.getFamily());
        warehouse.setMaxShelves(dto.getMaxShelves());

        // Convert list of shelf DTOs to domain Shelf objects
        if (dto.getShelves() != null) {
            List<Shelf> shelves = dto.getShelves().stream()
                    .map(shelfDto -> {
                        Shelf shelf = new Shelf();
                        shelf.setType(shelfDto.getType());
                        return shelf;
                    })
                    .collect(Collectors.toList());
            warehouse.setShelves(shelves);
        }

        return warehouse;
    }

    /**
     * Converts a domain Warehouse object into a response DTO to be returned to the client.
     * The DTO hides domain complexity and exposes only necessary data.
     */
    public WarehouseResponseDto toDto(Warehouse warehouse) {
        WarehouseResponseDto dto = new WarehouseResponseDto();
        dto.setId(warehouse.getId());
        dto.setClient(warehouse.getClient());
        dto.setInstallation(warehouse.getInstallation());
        dto.setFamily(warehouse.getFamily());
        dto.setMaxShelves(warehouse.getMaxShelves());

        // Convert list of Shelf domain objects to response DTOs
        if (warehouse.getShelves() != null) {
            List<ShelfResponseDto> shelfDtos = warehouse.getShelves().stream()
                    .map(shelf -> {
                        ShelfResponseDto shelfDto = new ShelfResponseDto();
                        shelfDto.setId(shelf.getId());
                        shelfDto.setType(shelf.getType());
                        return shelfDto;
                    })
                    .collect(Collectors.toList());
            dto.setShelves(shelfDtos);
        }

        return dto;
    }
}
