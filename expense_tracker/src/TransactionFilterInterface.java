import java.util.List;

import model.Transaction;

public interface TransactionFilterInterface {
    List<Transaction> filter(List<Transaction> transactions);
}
