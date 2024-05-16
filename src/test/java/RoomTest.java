import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(30.0, 2, true, "Spacious room with a view");
    }

    @Test
    void setIdIfNotSet() {
        assertNull(room.getId());
        room.setIdIfNotSet("123");
        assertEquals("123", room.getId());
        room.setIdIfNotSet("456");
        assertEquals("123", room.getId());
    }

    @Test
    void getters() {
        assertEquals(30.0, room.getArea());
        assertEquals(2, room.getFloor());
        assertTrue(room.hasKingSizeBed());
        assertEquals("Spacious room with a view", room.getDescription());
        assertEquals(0, room.getPeopleCapacity());
        assertNull(room.getEquipment());
        assertFalse(room.hasWifi());
        assertNull(room.getReservedDate());
    }

    @Test
    void setReserved() {
        assertNull(room.getReservedDate());
        LocalDate date = LocalDate.of(2024, 5, 16);
        room.setReserved(date);
        assertEquals(date, room.getReservedDate());
    }

    @Test
    void constructorWithFullDetails() {
        List<String> equipment = new ArrayList<>();
        equipment.add("TV");
        equipment.add("Mini fridge");
        Room fullDetailsRoom = new Room("123", "Luxury suite", 50.0, 3, true, 4, equipment, true);
        assertEquals("123", fullDetailsRoom.getId());
        assertEquals("Luxury suite", fullDetailsRoom.getDescription());
        assertEquals(50.0, fullDetailsRoom.getArea());
        assertEquals(3, fullDetailsRoom.getFloor());
        assertTrue(fullDetailsRoom.hasKingSizeBed());
        assertEquals(4, fullDetailsRoom.getPeopleCapacity());
        assertEquals(equipment, fullDetailsRoom.getEquipment());
        assertTrue(fullDetailsRoom.hasWifi());
        assertNull(fullDetailsRoom.getReservedDate());
    }
}