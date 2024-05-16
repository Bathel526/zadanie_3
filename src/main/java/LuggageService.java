import java.time.LocalDate;
import java.util.List;
public class LuggageService extends SpecialService {

    private List<String> Luggages;
    private LocalDate startOfDeposit;

    public LuggageService(List<String> Luggages, LocalDate startOfDeposit) {
        super("LuggageService");
        this.Luggages = Luggages;
        this.startOfDeposit = startOfDeposit;
    }

    @Override
    void orderService() {
        System.out.println("Hotel przechowuje bagaż");
        for (String luggage : Luggages) {
            System.out.println("- " + luggage);
        }
    }

    @Override
    int orderService_2() {
        return Luggages.size();
    }

    @Override
    LocalDate orderService_3() {
        LocalDate endOfDeposit = startOfDeposit.plusDays(7);
        return endOfDeposit;
    }
}