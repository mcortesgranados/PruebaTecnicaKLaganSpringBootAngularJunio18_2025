package com.mcg.klagan.pruebatecnica.adapter.in.web.dto;

import com.mcg.klagan.pruebatecnica.domain.model.ShelfType;
import jakarta.validation.constraints.NotNull;

/**
 * ShelfRequestDto represents a Data Transfer Object (DTO) used to receive shelf-related data
 * from the external world (e.g., a frontend application) into the system via an HTTP request.
 *
 * 🧱 Hexagonal Architecture (Ports & Adapters):
 * - This class is part of the **Inbound Adapter** (Web Layer).
 * - It serves as a **driving adapter** that translates external input into a format understood
 *   by the application's **input port** (use case).
 * - Shields the domain from direct exposure to framework- or transport-specific classes (like JSON).
 *
 * 🧼 Clean Architecture:
 * - Belongs to the **Interface Adapters** layer.
 * - Responsible for **mapping external data (HTTP request)** into internal application models or commands.
 * - Helps preserve the **independence** of the **use case and domain layers** from external technologies.
 *
 * 🧠 Domain-Driven Design (DDD):
 * - Although this class is not a domain model, it acts as a **DTO** to help convert input into a **domain command**.
 * - The `ShelfType` enum used here is a **Value Object** in the domain layer, promoting consistency.
 *
 * ✅ SOLID Principles:
 * ----------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Its only role is to carry shelf input data from the web layer into the use case layer.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - It can be extended (e.g., adding shelf labels or coordinates) without modifying existing code.
 *
 * ✅ ISP (Interface Segregation Principle):
 *   - Focused only on the fields required for shelf creation or update (no unnecessary input).
 *
 * 🔍 Validation:
 * - The `@NotNull` annotation ensures that input is validated at the web layer before it reaches the domain.
 *
 * 📦 Layer: Adapter → Inbound → Web (DTO)
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025
 */
public class ShelfRequestDto {

    /**
     * The type of the shelf to be created (must be A, B, C, or D).
     * This is a domain-level enumeration and acts as a Value Object in DDD.
     */
    @NotNull(message = "Shelf type must not be null")
    private ShelfType type;

    // --- Getters and Setters ---

    public ShelfType getType() {
        return type;
    }

    public void setType(ShelfType type) {
        this.type = type;
    }
}
