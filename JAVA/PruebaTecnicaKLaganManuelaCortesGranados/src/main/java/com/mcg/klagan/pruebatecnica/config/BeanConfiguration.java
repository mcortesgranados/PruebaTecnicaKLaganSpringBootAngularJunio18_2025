package com.mcg.klagan.pruebatecnica.config;

import com.mcg.klagan.pruebatecnica.domain.port.input.WarehouseUseCase;
import com.mcg.klagan.pruebatecnica.domain.port.output.WarehouseRepositoryPort;
import com.mcg.klagan.pruebatecnica.domain.service.WarehouseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class responsible for manual bean wiring.
 *
 * ✅ Clean Architecture:
 * - This configuration class acts as the composition root, wiring up use cases to their implementations.
 * - Decouples the application from the framework (e.g., avoids annotation-based service instantiation).
 *
 * 🧱 Hexagonal Architecture:
 * - Acts as a boundary adapter that links the input (use cases) and output (repository ports) to concrete implementations.
 *
 * ✅ SOLID Principles:
 * ----------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - This class only configures and provides beans for dependency injection.
 *
 * ✅ DIP (Dependency Inversion Principle):
 *   - High-level modules (e.g., WarehouseUseCase) do not depend on low-level modules (e.g., JPA).
 *   - This config ensures that the domain logic depends only on abstractions.
 *
 * 🚀 Testability & Flexibility:
 * - Enables easier mocking of ports for testing.
 * - Promotes framework-agnostic architecture (inversion of control container can change without impacting domain).
 *
 * 📦 Layer: Configuration (Dependency Injection / Bootstrap)
 * 👤 Author: Manuela Cortés Granados
 * 📅 Since: June 19, 2025 5:20 AM GMT -5 Bogota DC Colombia
 */
@Configuration
public class BeanConfiguration {

    /**
     * Provides a bean for the WarehouseUseCase input port.
     *
     * @param warehouseRepositoryPort the port interface for data persistence
     * @return an instance of WarehouseService that implements WarehouseUseCase
     */
    @Bean
    public WarehouseUseCase warehouseUseCase(WarehouseRepositoryPort warehouseRepositoryPort) {
        return new WarehouseService(warehouseRepositoryPort);
    }

    // 🔧 Additional beans can be registered here if more dependencies need decoupling
}
