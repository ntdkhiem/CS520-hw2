package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseTrackerModel {

  private List<Transaction> transactions;

  public ExpenseTrackerModel() {
    this.transactions = new ArrayList<>();
  }

  public void addTransaction(Transaction t) {
    this.transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    this.transactions.remove(t);
  }

  // return immutable list of transactions
  public List<Transaction> getTransactions() {
    return Collections.unmodifiableList(new ArrayList<>(this.transactions));
  }

  public List<Transaction> getFilteredTransactions(String category) {
    List<Transaction> filteredTransactions = new ArrayList<Transaction>();
    for (Transaction t : transactions) {
      if (t.getCategory().equals(category)) {
        filteredTransactions.add(t);
      }
    }
    return filteredTransactions;
  }

}