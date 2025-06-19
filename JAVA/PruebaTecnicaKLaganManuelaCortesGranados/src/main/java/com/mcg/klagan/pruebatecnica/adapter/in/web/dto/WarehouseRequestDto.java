package com.mcg.klagan.pruebatecnica.adapter.in.web.dto;

import com.mcg.klagan.pruebatecnica.domain.model.WarehouseFamily;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * WarehouseRequestDto is a Data Transfer Object (DTO) that encapsulates input data required
 * to create or update a warehouse via HTTP requests.
 *
 * 🧱 Hexagonal Architecture (Ports & Adapters):
 * - Part of the **Inbound Adapter** layer (Web interface).
 * - Converts external input (e.g. HTTP/JSON payloads) into internal representations.
 * - Decouples the domain and use cases from transport protocols and UI concerns.
 *
 * 🧼 Clean Architecture:
 * - Located in the **Interface Adapters** layer.
 * - Responsible for mediating between external models (UI/HTTP) and internal models (domain).
 * - Prevents leaking web-specific constructs (like annotations) into the domain layer.
 *
 * 🧠 Domain-Driven Design (DDD):
 * - This class is a **DTO**, which serves to map user input into the **domain model** (`Warehouse`, `Shelf`).
 * - `WarehouseFamily` is a **domain-level enumeration**, representing a core **concept** of the business.
 * - This class is not part of the domain itself but serves to feed data to it cleanly.
 *
 * ✅ SOLID Principles:
 * ----------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Responsible only for data transport and validation of warehouse input.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Can be extended with new fields (e.g., warehouse zone) without modifying domain logic.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *   - Can be substituted anywhere a DTO of the same structure is expected.
 *
 * ✅ ISP (Interface Segregation Principle):
 *   - Encapsulates only necessary fields to create a warehouse, without overburdening clients.
 *
 * 🔍 Input Validation:
 * - Ensures integrity and early validation via `javax.validation` annotations.
 * - Protects downstream layers from invalid or incomplete data.
 *
 * 📦 Layer: Adapter → Inbound → Web (DTO)
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 5:36 AM GMT -5 Bogota DC Colombia
 */
public class WarehouseRequestDto {

    /**
     * Name of the client that owns the warehouse.
     */
    @NotBlank(message = "El cliente no puede estar vacío")
    private String client;

    /**
     * Installation site where the warehouse is located.
     */
    @NotBlank(message = "La instalación no puede estar vacía")
    private String installation;

    /**
     * Warehouse family type (e.g., EST or ROB).
     * This is a strong domain concept.
     */
    @NotNull(message = "La familia es obligatoria")
    private WarehouseFamily family;

    /**
     * Maximum number of shelves allowed in the warehouse.
     */
    @Min(value = 1, message = "El número máximo de estanterías debe ser al menos 1")
    private int maxShelves;

    /**
     * List of shelf types to be installed in the warehouse.
     * Each shelf is validated individually using ShelfRequestDto.
     */
    @Valid
    @NotNull(message = "La lista de estanterías no puede ser nula")
    @Size(max = 100, message = "No se permiten más de 100 estanterías")
    private List<ShelfRequestDto> shelves;

    // --- Getters and Setters ---

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

    public List<ShelfRequestDto> getShelves() {
        return shelves;
    }

    public void setShelves(List<ShelfRequestDto> shelves) {
        this.shelves = shelves;
    }
}
