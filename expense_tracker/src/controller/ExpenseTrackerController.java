package controller;

import java.awt.SystemTray;
import java.util.ArrayList;
import java.util.List;
import controller.TransactionFilterInterface;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;

// import the TransactionFilterInterface
import controller.TransactionFilterInterface;

public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private List<Transaction> transactions = new ArrayList<>();

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;
  }

  public void refresh() {
    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);
  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods
  public void applyFilter(TransactionFilterInterface filter) {
    List<Transaction> filteredTransactions = filter.filter(transactions);
    // view.highlightFilteredRows(filteredTransactions);
  }

  public Boolean applyCategoryFilter(String category) {

    System.out.println("Filtering by category in controller");
    TransactionFilterInterface filter = new CategoryFilter(category);
    try{
      System.out.println("Categot in Controller" + category);
      System.out.println("Filtered Transactions in Controller" + model.getFilteredTransactions(category).size());
      // When the filtering matches any or multiple transactions, those rows are highlighted in green (RGB code:[173, 255, 168])
      view.highlightFilteredRows(model.getFilteredTransactions(category), new java.awt.Color(173, 255, 168));
      return true;
    } catch (Exception e) {
      System.out.println("Exception in controller" + e);
      return false;
    }
  }

  public void applyAmountFilter(double amount) {
    TransactionFilterInterface filter = new AmountFilter(amount);
    applyFilter(filter);
  }
}