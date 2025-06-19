package com.mcg.klagan.pruebatecnica.adapter.in.web.dto;

import com.mcg.klagan.pruebatecnica.domain.model.WarehouseFamily;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * WarehouseRequestDto is a Data Transfer Object (DTO) that encapsulates incoming data
 * required to create or update a warehouse.
 *
 * 📦 Layer: Adapter → Inbound → Web (DTO)
 * 🧱 Hexagonal Architecture Role:
 *      - Acts as an input adapter that receives data from external systems (HTTP requests).
 *      - Serves as a translation boundary between the web layer and the domain layer.
 *      - Helps isolate external protocols (JSON + HTTP + validation) from domain models.
 *
 * 🧼 Clean Architecture Alignment:
 *      - Part of the outer layer (interface adapters).
 *      - It does not contain domain logic.
 *      - It is used by controllers to accept and validate input before invoking use cases.
 *
 * 📐 Domain-Driven Design:
 *      - Does not belong to the domain model itself.
 *      - Is mapped to domain entities via a mapper (e.g., `WarehouseMapper`).
 *      - Uses the `WarehouseFamily` enum from the domain, preserving business semantics.
 *
 * 📐 SOLID Principles Applied:
 * ------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *      - Has only one responsibility: receive and validate input data for warehouse operations.
 *
 * ✅ OCP (Open/Closed Principle):
 *      - New fields or validations can be added without modifying other parts of the application.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *      - Instances of this DTO can be passed around in place of any superclass or interface (if present) without breaking behavior.
 *
 * ❌ ISP (Interface Segregation Principle): Not directly applicable to POJOs/DTOs.
 *
 * ❌ DIP (Dependency Inversion Principle): Not applicable, but this class depends only on stable domain elements (e.g., `WarehouseFamily`).
 *
 * ✅ Validation Layer:
 *      - Uses Jakarta Bean Validation to ensure correctness before domain transformation.
 *      - Prevents invalid states from leaking into domain logic.
 *
 * 🔐 Example Use Case:
 *      - Used in `WarehouseController.createWarehouse(...)` to receive a JSON payload.
 *      - Mapped via `WarehouseMapper` to the domain model `Warehouse`.
 *
 * 🧪 Validations:
 *      - `@NotBlank`, `@NotNull`, `@Min`, and `@Pattern` ensure business invariants at the boundary.
 *
 * Author: Manuela Cortés Granados
 * Since: 19 Junio 2025 4:02 AM GMT -5 Bogota DC Colombia
 */
public class WarehouseRequestDto {

    /**
     * Name of the client who owns the warehouse.
     * Cannot be blank.
     */
    @NotBlank(message = "El cliente no puede estar vacío")
    private String client;

    /**
     * Installation or site identifier.
     * Cannot be blank.
     */
    @NotBlank(message = "La instalación no puede estar vacía")
    private String installation;

    /**
     * Family classification of the warehouse (e.g., EST or ROB).
     * Cannot be null. Maps to domain enum `WarehouseFamily`.
     */
    @NotNull(message = "La familia es obligatoria")
    private WarehouseFamily family;

    /**
     * Maximum number of shelves allowed in the warehouse.
     * Must be at least 1.
     */
    @Min(value = 1, message = "El número máximo de estanterías debe ser al menos 1")
    private int maxShelves;

    /**
     * List of shelf types requested to be installed.
     * Each must be one of: A, B, C, or D (validated via regex).
     */
    private List<@Pattern(regexp = "A|B|C|D", message = "Tipo de estantería inválido") String> shelfTypes;

    // --- Getters y Setters ---

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

    public List<String> getShelfTypes() {
        return shelfTypes;
    }

    public void setShelfTypes(List<String> shelfTypes) {
        this.shelfTypes = shelfTypes;
    }
}
