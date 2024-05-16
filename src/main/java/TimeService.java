import java.time.LocalDateTime;
import java.time.LocalDate;
public class TimeService extends SpecialService {

    private int daysOfStay;
    private LocalDate arrivalDate;

    public TimeService(int daysOfStay, LocalDate arrivalDate) {
        super("TimeService");
        this.daysOfStay = daysOfStay;
        this.arrivalDate = arrivalDate;
    }

    @Override
    void orderService() {
        System.out.println("Aktualny czas: " + LocalDateTime.now());
    }
    @Override
    int orderService_2() {
        return daysOfStay;
    }
    @Override
    LocalDate orderService_3() {
        LocalDate dateOfDepature = arrivalDate.plusDays(daysOfStay);
        return dateOfDepature;
    }
}