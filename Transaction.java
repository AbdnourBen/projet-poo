import java.util.Date;

public class Transaction {
    private Property property;
    private Client client;
    private Date date;
    private String type;
    private double amount;

    public Transaction(Property property, Client client, Date date, String type, double amount) {
        this.property = property;
        this.client = client;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    // Getters and setters
}
