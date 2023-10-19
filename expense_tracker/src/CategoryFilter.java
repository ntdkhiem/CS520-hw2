import java.util.ArrayList;
import java.util.List;

import controller.InputValidation;
import model.Transaction;

public class CategoryFilter implements TransactionFilterInterface {
    private String categoryToFilter;

    public CategoryFilter(String categoryToFilter) {
        if (InputValidation.isValidCategory(categoryToFilter)) {
            this.categoryToFilter = categoryToFilter;
        }
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equals(categoryToFilter)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
}

