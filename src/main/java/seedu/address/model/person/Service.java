package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Person's service in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidService(String)}
 */
public class Service {
    public static final String MESSAGE_CONSTRAINTS =
            "Service should be alphanumeric words, separated by single spaces";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+( \\p{Alnum}+)*";
    public static final String DEFAULT_SERVICE = "Plumber";

    public final String service;

    /**
     * Constructs an {@code Service}.
     *
     * @param service A valid service.
     */
    public Service(String service) {
        requireNonNull(service);
        checkArgument(isValidService(service), MESSAGE_CONSTRAINTS);
        this.service = service;
    }

    /**
     * Returns true if a given string is a valid service.
     */
    public static Boolean isValidService(String str) {
        return str.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return service;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Service)) {
            return false;
        }

        Service otherService = (Service) other;
        return service.equals(otherService.service);
    }

    @Override
    public int hashCode() {
        return service.hashCode();
    }


}
