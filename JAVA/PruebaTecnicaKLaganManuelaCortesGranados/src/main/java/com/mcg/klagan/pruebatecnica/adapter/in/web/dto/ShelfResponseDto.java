package com.mcg.klagan.pruebatecnica.adapter.in.web.dto;

import com.mcg.klagan.pruebatecnica.domain.model.ShelfType;

/**
 * ShelfResponseDto is a Data Transfer Object used to expose shelf data in API responses.
 *
 * ✅ Clean Architecture:
 * - Belongs to the "adapter in" layer (input boundary for web interfaces).
 * - Acts as a contract between the web/controller layer and the domain/application layer.
 *
 * 🧱 Hexagonal Architecture:
 * - Used in the primary (driving) adapter to decouple the domain model from HTTP concerns.
 * - Prevents exposing domain objects directly to external consumers (maintains encapsulation).
 *
 * ✅ SOLID Principles:
 * ----------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - This class is solely responsible for representing shelf data in a response.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *   - Can be replaced with more specialized DTOs without breaking functionality.
 *
 * ✅ ISP (Interface Segregation Principle):
 *   - Avoids exposing unnecessary properties—only includes fields relevant for read operations.
 *
 * 🚀 Testability & Maintainability:
 * - Clean and minimal structure makes it ideal for controller unit tests and mocking.
 *
 * 📦 Layer: Adapter → Inbound → Web (DTO)
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 5:25 AM GMT -5 Bogota DC Colombia
 */
public class ShelfResponseDto {

    /**
     * Unique identifier of the shelf (used for tracking and persistence).
     */
    private Long id;

    /**
     * The type of shelf (A, B, C, D).
     */
    private ShelfType type;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShelfType getType() {
        return type;
    }

    public void setType(ShelfType type) {
        this.type = type;
    }
}
