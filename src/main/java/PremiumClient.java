import java.time.LocalDate;
public class PremiumClient extends Client {
    public enum premiumAccountType{
        PREMIUM,
        PREMIUM_PLUS
    }
    private premiumAccountType accountType;
    public PremiumClient(String id, LocalDate birthDate, String firstName, String lastName, premiumAccountType accountType) {
        super(id, birthDate, firstName, lastName);
        this.accountType = accountType;
    }

    public PremiumClient(String id, LocalDate birthDate, String firstName, String lastName, String sex, String phoneNumber, String email,premiumAccountType accountType) {
        super(id, birthDate, firstName, lastName, sex, phoneNumber, email);
        this.accountType = accountType;
    }
    public premiumAccountType getPremiumAccountType() {
        return accountType;
    }
    @Override
    public String getFullName(){
        String fullName = super.getFullName();
        return "[premium] " + fullName;
    }
}
