import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PremiumClientTest {

    private PremiumClient premiumClient;

    @BeforeEach
    void setUp() {
        premiumClient = new PremiumClient("123", LocalDate.of(1990, 5, 15), "John", "Doe", PremiumClient.premiumAccountType.PREMIUM);
    }

    @Test
    void getPremiumAccountType() {
        assertEquals(PremiumClient.premiumAccountType.PREMIUM, premiumClient.getPremiumAccountType());
    }

    @Test
    void getFullName() {
        assertEquals("[premium] John Doe", premiumClient.getFullName());
    }

    @Test
    void constructorWithFullDetails() {
        PremiumClient fullDetailsClient = new PremiumClient("123", LocalDate.of(1990, 5, 15), "John", "Doe", "M", "123-456-789", "john@example.com", PremiumClient.premiumAccountType.PREMIUM_PLUS);
        assertEquals(PremiumClient.premiumAccountType.PREMIUM_PLUS, fullDetailsClient.getPremiumAccountType());
        assertEquals("[premium] John Doe", fullDetailsClient.getFullName());
        assertEquals("123", fullDetailsClient.getId());
        assertEquals(LocalDate.of(1990, 5, 15), fullDetailsClient.getBirthDate());
        assertEquals("M", fullDetailsClient.getSex());
        assertEquals("123-456-789", fullDetailsClient.getPhoneNumber());
        assertEquals("john@example.com", fullDetailsClient.getEmail());
    }
}