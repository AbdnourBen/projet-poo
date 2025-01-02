package hjk;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String transactionType;
    private Property property;
    private Client client;
    private double paymentAmount;
    private String dateOfTransaction;

    public Transaction(String transactionType, Property property, Client client, double paymentAmount, String dateOfTransaction) {
        this.transactionType = transactionType;
        this.property = property;
        this.client = client;
        this.paymentAmount = paymentAmount;
        this.dateOfTransaction = dateOfTransaction;
    }

    //getters and setters

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    @Override
    public String toString() {
        return transactionType + " | " + property.getType() + " | " + client.getName() + " | " + paymentAmount + " | " + dateOfTransaction;
    }
}
