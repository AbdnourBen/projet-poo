import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void createTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Search for a transaction by the property and client
    public Transaction searchTransaction(Property property, Client client) {
        for (Transaction transaction : transactions) {
            if (transaction.getProperty().equals(property) && transaction.getClient().equals(client)) {
                return transaction;
            }
        }
        return null; // transaction not found
    }

    // Delete a transaction by the property and client
    public boolean deleteTransaction(Property property, Client client) {
        Transaction transaction = searchTransaction(property, client);
        if (transaction != null) {
            transactions.remove(transaction);
            return true; // transaction deleted successfully
        }
        return false; // transaction not found
    }
}
