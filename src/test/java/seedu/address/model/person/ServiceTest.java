package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ServiceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Service(null));
    }

    @Test
    public void constructor_invalidService_throwsIllegalArgumentException() {
        String invalidService = "";
        assertThrows(IllegalArgumentException.class, () -> new Service(invalidService));
    }

    @Test
    public void isValidService() {
        // null service
        assertThrows(NullPointerException.class, () -> Service.isValidService(null));

        // invalid service
        assertFalse(Service.isValidService("")); // empty string
        assertFalse(Service.isValidService(" ")); // spaces only
        assertFalse(Service.isValidService("plumb*")); // contains non-alphanumeric characters
        assertFalse(Service.isValidService("Air  Con")); // multiple consecutive spaces

        // valid service
        assertTrue(Service.isValidService("Plumbing")); // single word
        assertTrue(Service.isValidService("Air Con")); // words separated by single spaces
        assertTrue(Service.isValidService("24 Hour Service")); // alphanumeric with spaces
    }
}
