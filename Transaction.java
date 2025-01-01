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

    // Getters
    public Property getProperty() {
        return property;
    }

    public Client getClient() {
        return client;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    // Setters
    public void setProperty(Property property) {
        this.property = property;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
