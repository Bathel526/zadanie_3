import java.time.LocalDate;

public class RoomReservation {
    private LocalDate Date;
    private boolean isConfirmed;
    private Client client;
    private Room room;
    private String id;

    public RoomReservation(LocalDate Date){
        this.Date = Date;
        this.id = null;
        this.isConfirmed = false;
        this.room = room;
        this.client = client;
    }

    public void confirmReservation(){
        isConfirmed = true;
    }
    public Room getRoom(){
        return room;
    }

    public void setIdIfNotSet(String id) {
        if (this.id == null) {
            this.id = id;
        }
    }

    public Client getClient(){
        return client;
    }

    public Boolean getIsConfirmed(){
        return isConfirmed;
    }

    public String getId() {
        return id;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getDate() {
        return Date;
    }

}
