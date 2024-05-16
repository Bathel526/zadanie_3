import java.time.LocalDate;
import java.util.List;

public class Room {
    private String id;
    private String description;
    private double area;
    private int floor;
    private boolean hasKingSizeBed;
    private int peopleCapacity;
    private List<String> equipment;
    private boolean hasWifi;
    private LocalDate reservedDate;


    public Room(double area, int floor, boolean hasKingSizeBed, String description) {
        this.description = description;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
        this.id = null;
    }

    public Room(String id, String description, double area, int floor, boolean hasKingSizeBed) {
        this.id = id;
        this.description = description;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
    }

    public Room(String id, String description, double area, int floor, boolean hasKingSizeBed, int peopleCapacity, List<String> equipment, boolean hasWifi) {
        this.id = id;
        this.description = description;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
        this.peopleCapacity = peopleCapacity;
        this.equipment = equipment;
        this.hasWifi = hasWifi;
    }

    public void setIdIfNotSet(String id) {
        if( this.id == null) {
            this.id = id;
        }
    }
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getArea() {
        return area;
    }

    public int getFloor() {
        return floor;
    }

    public boolean hasKingSizeBed() {
        return hasKingSizeBed;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public boolean hasWifi() {
        return hasWifi;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }
    public void setReserved(LocalDate date) {
        this.reservedDate = date;
    }
}
