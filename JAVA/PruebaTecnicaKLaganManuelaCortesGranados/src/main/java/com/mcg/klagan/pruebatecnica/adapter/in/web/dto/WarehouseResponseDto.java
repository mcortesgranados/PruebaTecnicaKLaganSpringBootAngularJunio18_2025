package com.mcg.klagan.pruebatecnica.adapter.in.web.dto;

import com.mcg.klagan.pruebatecnica.domain.model.WarehouseFamily;

import java.util.List;

/**
 * WarehouseResponseDto is a Data Transfer Object (DTO) used to represent warehouse data
 * sent back to the client as part of a REST response.
 *
 * 📦 Layer: Adapter → Inbound → Web (DTO)
 * 🧱 Role in Hexagonal Architecture:
 *      - Acts as an output model from the web adapter to the external world (e.g., REST clients).
 *      - Isolates the domain model from serialization details and response formatting.
 *
 * 🧼 Clean Architecture Role:
 *      - Part of the "Interface Adapters" layer.
 *      - Serves as the response format to decouple external interfaces from domain entities.
 *      - Populated from the domain model using a `WarehouseMapper` to prevent leaking domain internals.
 *
 * 📐 Domain-Driven Design (DDD):
 *      - This DTO does not contain business logic, only represents the data view needed by clients.
 *      - Translates from domain aggregates (Warehouse, Shelf) into a simplified, flattened format.
 *      - References domain enum `WarehouseFamily`, which preserves semantic meaning.
 *
 * 📐 SOLID Principles:
 * ------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *      - This class only encapsulates response data for a warehouse resource.
 *      - Nested `ShelfDto` only holds shelf response data.
 *
 * ✅ OCP (Open/Closed Principle):
 *      - Easily extendable (e.g., you can add more fields like `createdAt`, `status`) without modifying other layers.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *      - The response DTO behaves consistently for all consumers of REST API output.
 *
 * ❌ ISP and ❌ DIP: Not directly applicable to DTOs.
 *
 * 🧪 Usage:
 *      - Returned in controller methods such as `GET /warehouses/{id}` or `POST /warehouses`.
 *      - Built from domain models through a `WarehouseMapper` or service layer transformation.
 *
 * Author: Manuela Cortés Granados
 * Since: 19 Junio 2025 4:06 AM GMT -5 Bogota DC Colombia
 */
public class WarehouseResponseDto {

    /**
     * Unique identifier for the warehouse.
     */
    private Long id;

    /**
     * The client who owns the warehouse.
     */
    private String client;

    /**
     * The physical or logical installation location.
     */
    private String installation;

    /**
     * Warehouse family classification (EST or ROB).
     */
    private WarehouseFamily family;

    /**
     * Maximum number of shelves that the warehouse supports.
     */
    private int maxShelves;

    /**
     * List of shelf DTOs contained in the warehouse.
     */
    private List<ShelfDto> shelves;

    // --- Getters and Setters ---

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

    public List<ShelfDto> getShelves() {
        return shelves;
    }

    public void setShelves(List<ShelfDto> shelves) {
        this.shelves = shelves;
    }

    /**
     * Nested DTO that represents a shelf in the warehouse.
     *
     * 💡 This is used to avoid leaking the domain entity ShelfEntity or Shelf.
     */
    public static class ShelfDto {

        /**
         * Unique identifier for the shelf.
         */
        private Long id;

        /**
         * Type of the shelf (A, B, C, or D).
         */
        private String type;

        public ShelfDto() {
        }

        public ShelfDto(Long id, String type) {
            this.id = id;
            this.type = type;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
