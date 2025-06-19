package com.mcg.klagan.pruebatecnica.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException forId(String resourceName, Long id) {
        return new ResourceNotFoundException(resourceName + " not found with id: " + id);
    }

    public static ResourceNotFoundException forName(String resourceName, String name) {
        return new ResourceNotFoundException(resourceName + " not found with name: " + name);
    }
}
