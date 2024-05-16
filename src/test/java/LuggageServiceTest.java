import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LuggageServiceTest {

    private LuggageService luggageService;

    @BeforeEach
    void setUp() {
        List<String> luggages = new ArrayList<>();
        luggages.add("Suitcase");
        luggages.add("Backpack");
        LocalDate startOfDeposit = LocalDate.now();
        luggageService = new LuggageService(luggages, startOfDeposit);
    }

    @Test
    void orderService() {
        assertEquals(2, luggageService.orderService_2());
        System.out.println("Expected output:");
        System.out.println("Hotel przechowuje bagaż");
        System.out.println("- Suitcase");
        System.out.println("- Backpack");
        System.out.println("Actual output:");
        luggageService.orderService();
    }

    @Test
    void orderService_3() {
        LocalDate expectedEndOfDeposit = LocalDate.now().plusDays(7);
        assertEquals(expectedEndOfDeposit, luggageService.orderService_3());
    }
}