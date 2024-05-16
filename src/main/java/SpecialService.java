import java.time.LocalDate;

public abstract class SpecialService {
    protected String name;

    public SpecialService(String name) {
        this.name = name;
    }

    abstract void orderService();
    abstract int orderService_2();
    abstract LocalDate orderService_3();
    public String getName(){
        return name;
    }
}