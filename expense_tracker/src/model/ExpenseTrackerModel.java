package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import controller.AmountFilter;
import controller.CategoryFilter;
import controller.TransactionFilterInterface;

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

  public List<Transaction> applyFilter(String filterType, Object filterConditionObject) {
    TransactionFilterInterface filter;
    if ("category".equals(filterType)) {
      filter = new CategoryFilter(filterConditionObject.toString());
      List<Transaction> filteredTransactions = filter.filter(this.transactions);
      System.out.println("Filtered Transactions in Controller" + filteredTransactions.size());
      return filteredTransactions;
    } else if ("amount".equals(filterType)) {
      filter = new AmountFilter((Double) filterConditionObject);
      List<Transaction> filteredTransactions = filter.filter(this.transactions);
      System.out.println("Filtered Transactions in Controller" + filteredTransactions.size());
      return filteredTransactions;
      // view.highlightFilteredRows(filteredTransactions, new java.awt.Color(173, 255,
      // 168));
    } else {
      throw new IllegalArgumentException("Invalid filter type");
    }
    // view.highlightFilteredRows(filteredTransactions);
  }

}