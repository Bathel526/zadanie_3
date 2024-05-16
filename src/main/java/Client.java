import java.time.LocalDate;
import java.time.Period;

public class Client {
    private String id;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private String sex;
    private String phoneNumber;
    private String email;

    public Client(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.id = null;
    }
    public Client(String id, LocalDate birthDate, String firstName, String lastName){
        this.id = id;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Client(String id, LocalDate birthDate, String firstName, String lastName, String sex, String phoneNumber, String email) {
        this.id = id;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getAge(){
    return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getFullName(){
        String firstNameCapitalized = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        String lastNameCapitalized = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        return firstNameCapitalized + " " + lastNameCapitalized;
    }

    public String getId() {
        return id;
    }

    public void setIdIfNotSet(String id) {
        if (this.id == null) {
            this.id = id;
        }
    }

    public boolean isUnderage() {
        LocalDate currentDate = LocalDate.now();
        LocalDate eighteenYearsAgo = currentDate.minusYears(18);
        return birthDate.isAfter(eighteenYearsAgo);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }
}
