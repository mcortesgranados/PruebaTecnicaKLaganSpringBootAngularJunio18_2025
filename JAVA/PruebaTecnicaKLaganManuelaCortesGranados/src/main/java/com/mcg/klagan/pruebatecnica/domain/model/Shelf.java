package com.mcg.klagan.pruebatecnica.domain.model;

import java.util.Objects;

/**
 * Shelf represents a domain entity that is part of the Warehouse aggregate.
 *
 * 🧱 Hexagonal Architecture:
 *   - Ubicada en el **dominio puro**, representa una pieza interna del agregado `Warehouse`.
 *   - No contiene dependencias externas (frameworks, JPA, etc.).
 *   - Es accedida a través del agregado principal y no de manera directa por capas externas.
 *
 * 🧼 Clean Architecture:
 *   - Modelo de dominio sin anotaciones ni lógica técnica.
 *   - Representa un objeto de negocio con atributos relevantes para las reglas de estanterías.
 *   - Gestionada desde el `Warehouse` aggregate, no desde fuera.
 *
 * 📐 Domain-Driven Design (DDD):
 *   - Entidad de dominio con identidad propia (`id`) y parte del agregado `Warehouse`.
 *   - Tiene una relación implícita con su raíz (`warehouseId`), que es controlada por el agregado.
 *   - No debe ser modificada sin pasar por el `Warehouse`, quien mantiene la integridad del conjunto.
 *
 * 📐 Principios SOLID:
 * ------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Representa solo una estantería dentro del dominio del almacén.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Puede extenderse (por ejemplo, agregar validaciones de tipo) sin modificar su estructura básica.
 *
 * ✅ LSP (Liskov Substitution Principle):
 *   - Se comporta correctamente al sustituir a otras entidades similares con contrato compatible.
 *
 * ❌ ISP / DIP: No aplican directamente a esta clase, que no representa una interfaz o dependencia.
 *
 * 📌 Importante:
 *   - No debe tener lógica de persistencia (como anotaciones @Entity).
 *   - No debería ser usada fuera del contexto de `Warehouse`.
 *
 * Autor: Manuela Cortés Granados
 * Desde: 19 Junio 2025
 */
public class Shelf {

    /**
     * Identificador único de la estantería (opcional en creación).
     */
    private Long id;

    /**
     * Tipo de estantería (A, B, C o D), definido por el enum {@link ShelfType}.
     */
    private ShelfType type;

    /**
     * Identificador del almacén al que pertenece esta estantería.
     * Importante para mantener la trazabilidad sin acoplamiento directo.
     */
    private Long warehouseId;

    // --- Getters y Setters ---

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

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    // --- Equals & HashCode (basados en ID para colecciones y pruebas) ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shelf)) return false;
        Shelf shelf = (Shelf) o;
        return Objects.equals(id, shelf.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
