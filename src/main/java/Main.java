import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client_1 = new Client("123456789", LocalDate.of(1990, 5, 15), "jaN", "kowaLski");
        Client client_2 = new Client("123456789", LocalDate.of(1998, 5, 15), "jaNek", "kowaLski", "male", "12319121", "ajhbdajhd@wp.pl");
        Client client_premium = new PremiumClient("123", LocalDate.of(1998, 5, 15), "Grzegorz", "Brzęczyszczykiewicz", "male", "12319121", "Grzegorz.Brzeczyszczykiewicz@example.com", PremiumClient.premiumAccountType.PREMIUM);
        Client client_3 = new Client("bart", "paw", LocalDate.of(1990, 4, 23));
        PremiumClient client_4 = new PremiumClient("123456789", LocalDate.of(1990, 5, 15), "jaN", "kowaLski", PremiumClient.premiumAccountType.PREMIUM);
        System.out.println(client_4.getPremiumAccountType());

        Room room_1 = new Room("12e12", "King room", 39.2, 5, true);
        Hotel hotel = new Hotel("Hilton");

        hotel.addClient(client_3);




        List<String> Luggages = new ArrayList<>(List.of("torba", "plecak", "walizka"));

        LuggageService service_1 = new LuggageService(Luggages, LocalDate.of(2023, 4, 1));
        TimeService service_2 = new TimeService(5, LocalDate.of(2024, 3, 12));

        hotel.addSpecialService(service_1);
        hotel.addSpecialService(service_2);

        service_1.orderService();
        service_1.orderService_2();
        service_2.orderService_3();

        System.out.println(service_1.getName());
        System.out.println("Hotel przechowuje dana ilosc bagazy: " + service_1.orderService_2());
        System.out.println("Hotel bedzie przechowywal bagaze do: " + service_1.orderService_3());

        System.out.println("Lista klientów: " + hotel.getClients());
        System.out.println("Lista usług: " + hotel.getSpecialServices());
    }
}