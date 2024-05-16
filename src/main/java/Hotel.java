import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import pl.wsb.hotel.exceptions.ClientNotFoundException;
import pl.wsb.hotel.exceptions.ReservationNotFoundException;
import pl.wsb.hotel.exceptions.RoomNotFoundException;
import pl.wsb.hotel.exceptions.RoomReservedException;
import pl.wsb.hotel.interfaces.HotelCapability;

public class Hotel implements HotelCapability {
    private String name;
    private List<Client> clients;
    private List<Room> rooms;
    private List<RoomReservation> reservations;
    private List<SpecialService> specialServices;

    public Hotel(String name) {
        this.name = name;
        this.clients = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.specialServices = new ArrayList<>();
    }

    @Override
    public String addClient(String firstName, String lastName, LocalDate birthDate) {
        Client client = new Client(firstName, lastName, birthDate);
        client.setIdIfNotSet(UUID.randomUUID().toString());
        clients.add(client);
        return client.getId();
    }

    @Override
    public String getClientFullName(String clientId) {
        if (clients.isEmpty()) {
            System.out.println("No clients found in the hotel.");
            return null;
        }
        try {
            for (Client client : clients) {
                if (client.getId().equals(clientId)) {
                    return client.getFullName();
                }
            }
            throw new ClientNotFoundException("Client not found with ID: " + clientId);
        } catch (ClientNotFoundException e) {
            System.err.println("Client not found: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int getNumberOfUnderageClients() {
        int count = 0;
        for (Client client : clients) {
            if (client.isUnderage()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String addRoom(double area, int floor, boolean hasKingSizeBed, String description) {
        Room room = new Room(area, floor, hasKingSizeBed, description);
        room.setIdIfNotSet(UUID.randomUUID().toString());
        rooms.add(room);
        return room.getId();
    }

    @Override
    public double getRoomArea(String roomId) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found in hotel");
            return 0.0;
        }
        try {
            for (Room room : rooms) {
                if (room.getId().equals(roomId)) {
                    return room.getArea();
                }
            }
            throw new RoomNotFoundException("Room not found: " + roomId);
        } catch (RoomNotFoundException e) {
            System.err.println("Room not found: " + e.getMessage());
            return 0.0;
        }
    }

    @Override
    public int getNumberOfRoomsWithKingSizeBed(int floor) {
        int count = 0;
        for (Room room : rooms) {
            if (room.hasKingSizeBed() && room.getFloor() == floor) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String addNewReservation(String clientId, String roomId, LocalDate date) {
        if (clients.isEmpty()) {
            System.out.println("No clients found in the hotel.");
            return null;
        } else if (rooms.isEmpty()) {
            System.out.println("No rooms in hotel.");
            return null;
        }
        try {
            Client foundClient = null;
            for (Client client : clients) {
                if (client.getId().equals(clientId)) {
                    foundClient = client;
                    break;
                }
            }
            if (foundClient == null) {
                throw new ClientNotFoundException("Client not found with id " + clientId);
            }

            Room foundRoom = null;
            for (Room room : rooms) {
                if (room.getId().equals(roomId)) {
                    foundRoom = room;
                    break;
                }
            }
            if (foundRoom == null) {
                throw new RoomNotFoundException("Room not found with id " + roomId);
            }
            if (isRoomReserved(roomId, date)) {
                System.out.println("Room " + roomId + " is already reserved for " + date);
                return null;
            }
            RoomReservation roomReservation = new RoomReservation(date);
            roomReservation.setIdIfNotSet(UUID.randomUUID().toString());
            roomReservation.setClient(foundClient);
            roomReservation.setRoom(foundRoom);
            foundRoom.setReserved(date);

            reservations.add(roomReservation);

            return roomReservation.getId();
        } catch (ClientNotFoundException | RoomNotFoundException e) {
            System.err.println("Not found: " + e.getMessage());
            return null;
        } catch (RoomReservedException e) {
            System.err.println("Room already reserved: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String confirmReservation(String reservationId) {
        for (RoomReservation reservation : reservations) {
            if (reservation.getId().equals(reservationId)) {
                reservation.confirmReservation();
                return "Reservation confirmed for ID: " + reservationId;
            }
        }
        // Jeśli nie znaleziono rezerwacji, zwróć null
        return null;
    }

    public boolean isRoomReserved(String roomId, LocalDate date) throws RoomNotFoundException {
        for (RoomReservation reservation : reservations) {
            if (reservation.getRoom().getId().equals(roomId) && reservation.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfUnconfirmedReservation(LocalDate date) {
        int count = 0;
        for (RoomReservation reservation : reservations) {
            if (!reservation.getIsConfirmed() && reservation.getDate().equals(date)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Collection<String> getRoomIdsReservedByClient(String clientId) throws ClientNotFoundException {
        List<String> roomIds = new ArrayList<>();
        boolean clientFound = false;
        for (Client client : clients) {
            if (client.getId().equals(clientId)) {
                clientFound = true;
                break;
            }
        }
        if (!clientFound) {
            throw new ClientNotFoundException("Client not found with ID: " + clientId);
        }

        for (RoomReservation reservation : reservations) {
            if (reservation.getClient().getId().equals(clientId)) {
                roomIds.add(reservation.getRoom().getId());
            }
        }
        return roomIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //clients
    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client){
        this.clients.add(client);
    }

    public void removeClient(Client client){
        this.clients.remove(client);
    }

    //rooms
    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room){
        this.rooms.add(room);
    }

    public void removeRoom(Room room){
        this.rooms.remove(room);
    }

    public List<RoomReservation> getReservations() {
        return reservations;
    }

    public void addReservation(RoomReservation reservation){
        this.reservations.add(reservation);
    }

    public void removeReservation(RoomReservation reservation){
        this.reservations.remove(reservation);
    }

    //services


    public List<SpecialService> getSpecialServices() {
        return specialServices;
    }

    public void addSpecialService(SpecialService specialService){
        this.specialServices.add(specialService);
    }

    public void removeSpecialService(SpecialService specialService){
        this.specialServices.remove(specialService);
    }
}