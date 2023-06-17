package guru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @Test
    void loginTest() {
        System.out.println("COMMON TEST");
    }

    @Test
    @Tag("web")
    void profileTest() {
        System.out.println("COMMON TEST");
    }

    @Tags({
            @Tag("smoke")
    })
    @DisplayName("Емайл должен отправляться после регистрации пользователя")
    @Test
    void sendEmailTest() {
        System.out.println("SMOKE TEST");
    }

    @WebSmokeTest
    /**
     * Protected constructor allowing subclassing but not direct instantiation.
     *
     * @since 5.3
     */
    void simpleTest() {
        System.out.println("SMOKE TEST");
        Assertions.assertTrue(3 > 2); // Объяснение сложного кода
    }
}
