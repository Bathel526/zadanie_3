import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RoomReservationTest {

    private RoomReservation roomReservation;
    private Client client;
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(30.0, 2, true, "Spacious room with a view");
        client = new Client("123", LocalDate.of(1990, 5, 15), "John", "Doe");
        roomReservation = new RoomReservation(LocalDate.of(2024, 5, 16));
    }

    @Test
    void confirmReservation() {
        assertFalse(roomReservation.getIsConfirmed());
        roomReservation.confirmReservation();
        assertTrue(roomReservation.getIsConfirmed());
    }

    @Test
    void getters() {
        assertEquals(LocalDate.of(2024, 5, 16), roomReservation.getDate());
        assertNull(roomReservation.getId());
        assertNull(roomReservation.getClient());
        assertNull(roomReservation.getRoom());
        assertFalse(roomReservation.getIsConfirmed());
    }

    @Test
    void setIdIfNotSet() {
        assertNull(roomReservation.getId());
        roomReservation.setIdIfNotSet("123");
        assertEquals("123", roomReservation.getId());

        // Sprawdzamy czy ID nie jest zmieniane gdy już jest ustawione
        roomReservation.setIdIfNotSet("456");
        assertEquals("123", roomReservation.getId());
    }

    @Test
    void setters() {
        roomReservation.setClient(client);
        assertEquals(client, roomReservation.getClient());

        roomReservation.setRoom(room);
        assertEquals(room, roomReservation.getRoom());
    }
}