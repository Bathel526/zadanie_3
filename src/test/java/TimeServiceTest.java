import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TimeServiceTest {

    private TimeService timeService;

    @BeforeEach
    void setUp() {
        timeService = new TimeService(5, LocalDate.of(2024, 5, 16));
    }

    @Test
    void orderService() {
        assertDoesNotThrow(() -> timeService.orderService());
    }

    @Test
    void orderService_2() {
        assertEquals(5, timeService.orderService_2());
    }

    @Test
    void orderService_3() {
        LocalDate expectedDate = LocalDate.of(2024, 5, 21);
        assertEquals(expectedDate, timeService.orderService_3());
    }
}