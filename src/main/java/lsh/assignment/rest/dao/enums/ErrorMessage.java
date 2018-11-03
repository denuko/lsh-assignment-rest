package lsh.assignment.rest.dao.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    NOT_FOUND("Entity not found with id: "),
    INVALID("Invalid field: "),
    INVALID_LICENSE_PLATE("License plate should be 6 characters (letters and digits only), only greek alphabet is valid"),
    INVALID_POSTAL_CODE("Postal code must be valid (5 digits, numbers only)"),
    INVALID_VEHICLE_NAME("Vehicle name should be 3-30 characters long"),
    INVALID_WHEELLIST_SIZE("The vehicle should have 4 or 6 wheels"),
    INVALID_WHEEL_PRESSURE("The difference between pressure (from min to max pressure) can't be greater than 0.5");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
