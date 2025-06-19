package com.mcg.klagan.pruebatecnica.domain.model;

import java.util.EnumSet;

/**
 * WarehouseFamily is a domain enum that defines the category (family) of a warehouse.
 *
 * 🎯 Purpose in Domain-Driven Design (DDD):
 *      - Encapsulates a business rule: defines which shelf types are allowed per family.
 *      - Adds behavior to what would otherwise be a simple constant enum.
 *      - Represents a domain concept clearly and unambiguously.
 *
 * 🧱 Role in Hexagonal Architecture:
 *      - This enum belongs to the domain model, the innermost and purest layer.
 *      - It contains no technical or infrastructural dependencies.
 *      - It is used by application services, use cases, and also mapped from/to persistence adapters.
 *
 * 📐 SOLID Principles Applied:
 * ------------------------------------------------------------------------------
 * ✅ Single Responsibility Principle (SRP):
 *      - This enum has a single responsibility: define and expose allowed shelf types per family.
 *      - It contains no unrelated logic or infrastructure concerns.
 *
 * ✅ Open/Closed Principle (OCP):
 *      - New families can be added by adding new enum constants (open for extension).
 *      - Existing logic remains untouched (closed for modification).
 *
 * ✅ Liskov Substitution Principle (LSP):
 *      - Each enum constant can be substituted as a valid `WarehouseFamily` with predictable behavior.
 *
 * ❌ Interface Segregation Principle (ISP): Not applicable for enums.
 * ❌ Dependency Inversion Principle (DIP): Not applicable directly, but respected in layered architecture.
 *
 * ✅ Immutability:
 *      - EnumSet is immutable from the outside via encapsulation.
 *      - Promotes thread safety and reliable behavior in business logic.
 *
 * Usage example:
 *   - `WarehouseFamily.EST.getAllowedTypes()` returns `[A, B, C]`.
 *   - Used during shelf validation and permutation calculations.
 *
 * Author: Manuela Cortés Granados
 * Since: 19 Junio 2025 3:44 AM GMT -5 Bogota DC Colombia
 */
public enum WarehouseFamily {

    /**
     * EST family: accepts shelf types A, B, and C.
     */
    EST(EnumSet.of(ShelfType.A, ShelfType.B, ShelfType.C)),

    /**
     * ROB family: accepts shelf types A, C, and D.
     */
    ROB(EnumSet.of(ShelfType.A, ShelfType.C, ShelfType.D));

    /**
     * Allowed shelf types for this family.
     */
    private final EnumSet<ShelfType> allowedTypes;

    /**
     * Constructor that injects allowed shelf types per family.
     */
    WarehouseFamily(EnumSet<ShelfType> allowedTypes) {
        this.allowedTypes = allowedTypes;
    }

    /**
     * Returns the allowed shelf types for this warehouse family.
     * Immutable externally.
     */
    public EnumSet<ShelfType> getAllowedTypes() {
        return allowedTypes;
    }
}
