import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void createTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Other methods (search, delete)
}
