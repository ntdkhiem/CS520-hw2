import java.util.ArrayList;
import java.util.List;

import controller.InputValidation;
import model.Transaction;

public class AmountFilter implements TransactionFilterInterface{
    private double amountToFilter;

    public AmountFilter(double amountToFilter) {
        if (InputValidation.isValidAmount(amountToFilter)) {
            this.amountToFilter = amountToFilter;
        }
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() == amountToFilter) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
}
