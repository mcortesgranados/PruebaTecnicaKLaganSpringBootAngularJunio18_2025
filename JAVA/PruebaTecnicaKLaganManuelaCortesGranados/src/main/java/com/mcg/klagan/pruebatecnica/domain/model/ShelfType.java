package com.mcg.klagan.pruebatecnica.domain.model;

/**
 * ShelfType is a domain-level enum representing the possible types of shelves
 * that can be installed in a warehouse.
 *
 * 🎯 Purpose in Domain-Driven Design (DDD):
 *      - Models a ubiquitous concept in the domain: shelf classification by type.
 *      - Provides a type-safe, restricted set of values for business logic and validation.
 *      - Prevents invalid shelf types from being introduced into the system.
 *
 * 🧱 Role in Hexagonal Architecture:
 *      - Located in the domain model (core, independent of frameworks).
 *      - Used in core logic such as shelf validation, warehouse configuration, and permutation rules.
 *      - Shared across domain services, use cases, and adapted to persistence (e.g., stored as a String via JPA).
 *
 * 📐 SOLID Principles Applied:
 * ------------------------------------------------------------------------------
 * ✅ Single Responsibility Principle (SRP):
 *      - Has a single job: represent shelf types in a fixed, valid, and type-safe manner.
 *
 * ✅ Open/Closed Principle (OCP):
 *      - You can extend the system with new shelf types (e.g., E, F) without modifying consumers (like permutation logic).
 *
 * ✅ Liskov Substitution Principle (LSP):
 *      - Any ShelfType value (A, B, C, D) can be used interchangeably in business rules expecting a valid shelf type.
 *
 * ❌ Interface Segregation Principle (ISP): Not applicable to enums.
 * ❌ Dependency Inversion Principle (DIP): Not applicable directly, but this enum is used in high-level domain logic only.
 *
 * ✅ Immutability:
 *      - Being an enum, it is inherently immutable, promoting thread safety and predictable logic.
 *
 * Usage examples:
 *      - Used in WarehouseFamily to define allowed shelf types per family.
 *      - Serialized/deserialized in DTOs or entities as string values.
 *      - Used in warehouse permutation logic to generate valid combinations.
 *
 * Author: Manuela Cortés Granados
 * Since: 19 Junio 2025 3:47 AM GMT -5 Bogota DC Colombia
 */
public enum ShelfType {
    /**
     * Shelf type A — common to both EST and ROB warehouse families.
     */
    A,

    /**
     * Shelf type B — allowed only in EST family.
     */
    B,

    /**
     * Shelf type C — allowed in both EST and ROB.
     */
    C,

    /**
     * Shelf type D — allowed only in ROB family.
     */
    D
}
