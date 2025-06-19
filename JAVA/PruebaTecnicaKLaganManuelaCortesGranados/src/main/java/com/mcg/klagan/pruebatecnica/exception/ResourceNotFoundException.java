package com.mcg.klagan.pruebatecnica.exception;

/**
 * Custom exception to represent cases where a requested resource is not found.
 *
 * ✅ Clean Architecture:
 * - This exception belongs to the domain or shared kernel layer of the application.
 * - Promotes clarity and semantic expressiveness for domain-driven error scenarios.
 *
 * 🧱 Hexagonal Architecture:
 * - Thrown from the core domain or application service layer (inside the hexagon).
 * - Captured and translated by the inbound adapter layer (e.g., GlobalExceptionHandler).
 *
 * ✅ SOLID Principles:
 * ----------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Solely responsible for signaling resource-not-found errors in a meaningful way.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Can be extended with static factory methods for other types of identifiers (e.g., UUID).
 *
 * 🧪 Improves testability:
 * - Use this exception in services to cleanly separate normal and exceptional flows.
 *
 * 📦 Layer: Domain / Shared Exception Layer
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 5:02 AM GMT -5 Bogota DC Colombia
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Creates a new exception instance with a descriptive message.
     * @param message the detail message about the missing resource
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Factory method for missing resource identified by numeric ID.
     * @param resourceName the name of the resource (e.g., "Warehouse")
     * @param id the numeric identifier
     * @return a properly formatted exception
     */
    public static ResourceNotFoundException forId(String resourceName, Long id) {
        return new ResourceNotFoundException(resourceName + " not found with id: " + id);
    }

    /**
     * Factory method for missing resource identified by name.
     * @param resourceName the name of the resource (e.g., "Client")
     * @param name the name identifier
     * @return a properly formatted exception
     */
    public static ResourceNotFoundException forName(String resourceName, String name) {
        return new ResourceNotFoundException(resourceName + " not found with name: " + name);
    }
}
