package com.mcg.klagan.pruebatecnica.adapter.out.persistence.init;

import com.mcg.klagan.pruebatecnica.adapter.out.persistence.SpringWarehouseRepository;
import com.mcg.klagan.pruebatecnica.adapter.out.persistence.entity.ShelfEntity;
import com.mcg.klagan.pruebatecnica.adapter.out.persistence.entity.WarehouseEntity;
import com.mcg.klagan.pruebatecnica.domain.model.ShelfType;
import com.mcg.klagan.pruebatecnica.domain.model.WarehouseFamily;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DataInitializer precarga datos de prueba en la base de datos cuando la aplicación inicia,
 * pero solo en perfiles distintos de "test".
 *
 * 🧱 Rol en Arquitectura Hexagonal:
 *   - Se encuentra en el adaptador de salida (`adapter.out.persistence.init`) y depende de infraestructura (Spring Boot, repositorio JPA).
 *   - Utiliza entidades persistentes (`WarehouseEntity`, `ShelfEntity`) sin mezclar lógica de dominio.
 *
 * 🧼 Clean Architecture:
 *   - Se ubica en la capa exterior (infraestructura) para inyectar datos sin comprometer la lógica del dominio.
 *   - No contiene lógica de negocio: sólo prepara datos válidos que cumplen con las restricciones del dominio.
 *   - Utiliza objetos de persistencia, no del dominio puro (se evita acoplamiento directo).
 *
 * 📐 Domain-Driven Design (DDD):
 *   - No interactúa con aggregates del dominio directamente.
 *   - Usa `WarehouseFamily` y `ShelfType` desde el dominio para mantener la coherencia semántica.
 *
 * 📐 Principios SOLID:
 * ------------------------------------------------------------------------------
 * ✅ SRP (Single Responsibility Principle):
 *   - Esta clase tiene un único propósito: inicializar datos de ejemplo.
 *
 * ✅ OCP (Open/Closed Principle):
 *   - Puede ampliarse fácilmente con nuevos almacenes/estanterías sin alterar la lógica existente.
 *
 * ❌ LSP, ❌ ISP, ❌ DIP:
 *   - No aplican directamente aquí por tratarse de una clase de configuración temporal.
 *
 * ✅ Buenas Prácticas:
 *   - Uso de `@Profile("!test")` para evitar ejecución en entornos de test (aislamiento de pruebas).
 *   - Separación de lógica de inicialización del resto de la lógica de negocio o aplicación.
 *
 * 🚀 Se ejecuta automáticamente gracias a `CommandLineRunner` en el arranque del contexto de Spring.
 *
 * Autor: Manuela Cortés Granados
 * Desde: 19 Junio 2025 4:14 AM -5 GMT Bogota DC Colombia
 */
@Component
@Profile("!test") // Solo carga en perfiles diferentes a "test"
public class DataInitializer implements CommandLineRunner {

    private final SpringWarehouseRepository warehouseRepository;

    public DataInitializer(SpringWarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void run(String... args) {
        if (warehouseRepository.count() == 0) {
            // 🚚 Almacén tipo EST con estanterías A y B válidas
            WarehouseEntity estWarehouse = new WarehouseEntity();
            estWarehouse.setClient("Cliente Alfa");
            estWarehouse.setInstallation("Instalación Central");
            estWarehouse.setFamily(WarehouseFamily.EST);
            estWarehouse.setMaxShelves(2);

            ShelfEntity shelf1 = new ShelfEntity();
            shelf1.setType(ShelfType.A);
            shelf1.setWarehouse(estWarehouse);

            ShelfEntity shelf2 = new ShelfEntity();
            shelf2.setType(ShelfType.B);
            shelf2.setWarehouse(estWarehouse);

            estWarehouse.setShelves(List.of(shelf1, shelf2));
            warehouseRepository.save(estWarehouse);

            // 🤖 Almacén tipo ROB sin estanterías inicialmente
            WarehouseEntity robWarehouse = new WarehouseEntity();
            robWarehouse.setClient("Cliente Beta");
            robWarehouse.setInstallation("Instalación Norte");
            robWarehouse.setFamily(WarehouseFamily.ROB);
            robWarehouse.setMaxShelves(3);

            warehouseRepository.save(robWarehouse);

            System.out.println("📦 Datos de prueba cargados exitosamente.");
        }
    }
}
