import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import pl.wsb.hotel.exceptions.RoomNotFoundException;
import pl.wsb.hotel.exceptions.ClientNotFoundException;
import java.util.Collection;
import java.util.List;
import java.time.LocalDate;

public class HotelTest {

    private Hotel hotel;
    private Client client;
    private Room room;
    private RoomReservation roomReservation;
    private SpecialService specialService;

    @BeforeEach
    void setUp() {
        hotel = new Hotel("Test Hotel");
        client = new Client("1", LocalDate.of(1990, 5, 15), "John", "Doe");
        room = new Room(30.0, 2, true, "Spacious room with a view");
        roomReservation = new RoomReservation(LocalDate.of(2024, 5, 16));
        specialService = new TimeService(5, LocalDate.of(2024, 5, 16));
    }

    @Test
    void addClientTest() {
        String clientId = hotel.addClient("John", "Doe", LocalDate.of(1990, 5, 10));
        assertNotNull(clientId);
    }

    @Test
    void getClientFullNameTest() {
        String clientId = hotel.addClient("John", "Doe", LocalDate.of(1990, 5, 10));
        String fullName = hotel.getClientFullName(clientId);
        assertEquals("John Doe", fullName);
    }

    @Test
    void getNumberOfUnderageClientsTest() {
        hotel.addClient("John", "Doe", LocalDate.of(2010, 5, 10)); // underage client
        hotel.addClient("Jane", "Smith", LocalDate.of(1990, 5, 10)); // adult client
        int underageClients = hotel.getNumberOfUnderageClients();
        assertEquals(1, underageClients);
    }

    @Test
    void addRoomTest() {
        String roomId = hotel.addRoom(25.0, 1, true, "Single Room");
        assertNotNull(roomId);
    }

    @Test
    void getRoomAreaTest() {
        String roomId = hotel.addRoom(25.0, 1, true, "Single Room");
        double roomArea = hotel.getRoomArea(roomId);
        assertEquals(25.0, roomArea);
    }

    public void testGetNumberOfRoomsWithKingSizeBedWhenRoomsExist() {
        hotel.addRoom(20.0, 1, true, "Room with king size bed on first floor");
        hotel.addRoom(25.0, 1, false, "Room without king size bed on first floor");
        hotel.addRoom(30.0, 2, true, "Room with king size bed on second floor");
        int expectedNumberOfRooms = 2;
        int actualNumberOfRooms = hotel.getNumberOfRoomsWithKingSizeBed(1);
        assertEquals(expectedNumberOfRooms, actualNumberOfRooms);
    }

    @Test
    public void testGetNumberOfRoomsWithKingSizeBedWhenNoRoomsExist() {
        int actualNumberOfRooms = hotel.getNumberOfRoomsWithKingSizeBed(1);
        assertEquals(0, actualNumberOfRooms);
    }

    @Test
    public void testGetNumberOfRoomsWithKingSizeBedWhenNoRoomsHaveKingSizeBed() {
        // Dodajemy kilka pokoi do hotelu, ale żaden nie będzie miał king size bed
        hotel.addRoom(20.0, 1, false, "Room without king size bed on first floor");
        hotel.addRoom(25.0, 2, false, "Room without king size bed on second floor");
        int actualNumberOfRooms = hotel.getNumberOfRoomsWithKingSizeBed(1);
        assertEquals(0, actualNumberOfRooms);
    }

    @Test
    public void testAddNewReservationWhenClientAndRoomExistAndRoomIsAvailable() {
        // Given: Dodajemy klienta i pokój do hotelu
        String clientId = hotel.addClient("John", "Doe", LocalDate.of(1990, 5, 15));
        String roomId = hotel.addRoom(25.0, 1, false, "Single bed room");

        // When: Dodajemy nową rezerwację dla istniejącego klienta, pokoju i daty
        String reservationId = hotel.addNewReservation(clientId, roomId, LocalDate.now());

        // Then: Sprawdzamy, czy rezerwacja została dodana poprawnie (ID nie jest null)
        assertNotNull(reservationId);

        // Dodatkowo, można sprawdzić, czy rezerwacja jest obecna na liście rezerwacji w hotelu
        assertTrue(hotel.getReservations().stream().anyMatch(reservation -> reservation.getId().equals(reservationId)));
    }

    @Test
    public void testAddNewReservationWhenClientDoesNotExist() {
        String reservationId = hotel.addNewReservation("nonexistent_client_id", "room_id", LocalDate.now());
        assertNull(reservationId);
    }

    @Test
    public void testAddNewReservationWhenRoomDoesNotExist() {
        String clientId = hotel.addClient("John", "Doe", LocalDate.of(1990, 5, 15));
        String reservationId = hotel.addNewReservation(clientId, "nonexistent_room_id", LocalDate.now());
        assertNull(reservationId);
    }

    @Test
    public void testAddNewReservationWhenRoomIsAlreadyReservedOnGivenDate() {
        String clientId = hotel.addClient("John", "Doe", LocalDate.of(1990, 5, 15));
        String roomId = hotel.addRoom(25.0, 1, false, "Single bed room");
        hotel.addNewReservation(clientId, roomId, LocalDate.now());
        String reservationId = hotel.addNewReservation(clientId, roomId, LocalDate.now());
        assertNull(reservationId);
    }

    @Test
    void confirmReservation() {
        String clientId = hotel.addClient("John", "Doe", LocalDate.of(1990, 5, 15));
        String roomId = hotel.addRoom(25.0, 1, true, "Single room");
        String reservationId = hotel.addNewReservation(clientId, roomId, LocalDate.now());
        String confirmationMessage = hotel.confirmReservation(reservationId);
        assertNotNull(confirmationMessage);
        assertEquals("Reservation confirmed for ID: " + reservationId, confirmationMessage);
    }

    @Test
    void confirmReservation_ReservationNotFound() {
        String nonExistentReservationId = "nonExistentReservationId";
        assertNull(hotel.confirmReservation(nonExistentReservationId));
    }

    @Test
    public void testIsRoomReservedWhenRoomIsReservedOnGivenDate() {
        String clientId = hotel.addClient("John", "Doe", LocalDate.of(1990, 5, 15));
        String roomId = hotel.addRoom(25.0, 1, false, "Single bed room");
        LocalDate reservationDate = LocalDate.now();
        hotel.addNewReservation(clientId, roomId, reservationDate);
        boolean isReserved = hotel.isRoomReserved(roomId, reservationDate);
        assertTrue(isReserved);
    }

    @Test
    void getRoomIdsReservedByClient() {
        String clientId = hotel.addClient("John", "Doe", LocalDate.of(1990, 5, 15));
        String roomId1 = hotel.addRoom(25.0, 1, true, "Single room");
        String roomId2 = hotel.addRoom(30.0, 2, false, "Double room");
        String roomId3 = hotel.addRoom(35.0, 1, true, "Single room");
        hotel.addNewReservation(clientId, roomId1, LocalDate.now());
        hotel.addNewReservation(clientId, roomId2, LocalDate.now());
        hotel.addNewReservation(clientId, roomId3, LocalDate.now());
        Collection<String> reservedRoomIds = null;
        try {
            reservedRoomIds = hotel.getRoomIdsReservedByClient(clientId);
        } catch (ClientNotFoundException e) {
            fail("ClientNotFoundException should not be thrown in this case");
        }
        assertNotNull(reservedRoomIds);
        assertEquals(3, reservedRoomIds.size());
        assertTrue(reservedRoomIds.contains(roomId1));
        assertTrue(reservedRoomIds.contains(roomId2));
        assertTrue(reservedRoomIds.contains(roomId3));
    }

    @Test
    void getRoomIdsReservedByClient_ClientNotFound() {
        assertThrows(ClientNotFoundException.class, () -> hotel.getRoomIdsReservedByClient("nonExistentClientId"));
    }

    @Test
    void getName() {
        assertEquals("Test Hotel", hotel.getName());
    }

    @Test
    void clientsOperations() {
        assertTrue(hotel.getClients().isEmpty());

        hotel.addClient(client);
        assertEquals(1, hotel.getClients().size());

        hotel.removeClient(client);
        assertTrue(hotel.getClients().isEmpty());
    }

    @Test
    void roomsOperations() {
        assertTrue(hotel.getRooms().isEmpty());

        hotel.addRoom(room);
        assertEquals(1, hotel.getRooms().size());

        hotel.removeRoom(room);
        assertTrue(hotel.getRooms().isEmpty());
    }

    @Test
    void reservationsOperations() {
        assertTrue(hotel.getReservations().isEmpty());

        hotel.addReservation(roomReservation);
        assertEquals(1, hotel.getReservations().size());

        hotel.removeReservation(roomReservation);
        assertTrue(hotel.getReservations().isEmpty());
    }

    @Test
    void specialServicesOperations() {
        assertTrue(hotel.getSpecialServices().isEmpty());

        hotel.addSpecialService(specialService);
        assertEquals(1, hotel.getSpecialServices().size());

        hotel.removeSpecialService(specialService);
        assertTrue(hotel.getSpecialServices().isEmpty());
    }
}

