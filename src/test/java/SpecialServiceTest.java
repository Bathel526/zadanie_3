import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SpecialServiceTest {

    private SpecialService specialService;

    @BeforeEach
    void setUp() {
        specialService = new SpecialServiceImpl("TestService");
    }

    @Test
    void getName() {
        assertEquals("TestService", specialService.getName());
    }

    @Test
    void orderService() {
        assertDoesNotThrow(() -> specialService.orderService());
    }

    @Test
    void orderService_2() {
        assertEquals(0, specialService.orderService_2());
    }

    @Test
    void orderService_3() {
        assertNull(specialService.orderService_3());
    }

    private static class SpecialServiceImpl extends SpecialService {
        public SpecialServiceImpl(String name) {
            super(name);
        }

        @Override
        void orderService() {
            // Do nothing for testing purposes
        }

        @Override
        int orderService_2() {
            return 0;
        }

        @Override
        LocalDate orderService_3() {
            return null;
        }
    }
}