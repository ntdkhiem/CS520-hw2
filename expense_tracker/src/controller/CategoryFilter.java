package controller;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class CategoryFilter implements TransactionFilterInterface {
    private String categoryToFilter;

    public CategoryFilter(String categoryToFilter) {
        if (InputValidation.isValidCategory(categoryToFilter)) {
            this.categoryToFilter = categoryToFilter;
            System.out.println("CategoryFilter constructor called: " + categoryToFilter);
        }
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<Transaction>();
        System.out.println("CategoryFilter filter() called");
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equals(categoryToFilter)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
}
