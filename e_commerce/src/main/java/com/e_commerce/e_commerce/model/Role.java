package com.e_commerce.e_commerce.model;


import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    CUSTOMER, ADMIN;

    @JsonCreator
    public static Role fromString(String value) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
