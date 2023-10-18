package model;

import java.util.ArrayList;
import java.util.List;

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

  public List<Transaction> getTransactions() {
    return this.transactions;
  }

}