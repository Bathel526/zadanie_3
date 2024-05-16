import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client("Pewien", "Znany", LocalDate.of(1990, 5, 15));
    }

    @Test
    void getAge() {
        assertEquals(34, client.getAge());
    }

    @Test
    void getFullName() {
        assertEquals("Pewien Znany", client.getFullName());
    }

    @Test
    void isUnderage() {
        assertFalse(client.isUnderage());
        Client underageClient = new Client("Janek", "Bosko", LocalDate.of(2012, 5, 15));
        assertTrue(underageClient.isUnderage());
    }

    @Test
    void setIdIfNotSet() {
        // Sprawdzamy czy ID jest ustawiane tylko gdy jest null
        assertNull(client.getId());
        client.setIdIfNotSet("lubieplacki");
        assertEquals("lubieplacki", client.getId());


        client.setIdIfNotSet("456");
        assertEquals("lubieplacki", client.getId());
    }

    @Test
    void getAndSetPhoneNumber() {
        assertNull(client.getPhoneNumber());
        client.setPhoneNumber("123-456-789");
        assertEquals("123-456-789", client.getPhoneNumber());
    }

    @Test
    void getAndSetEmail() {
        // Testujemy getter i setter dla adresu email
        assertNull(client.getEmail());
        client.setEmail("jakismail@com");
        assertEquals("jakismail@com", client.getEmail());
    }
}