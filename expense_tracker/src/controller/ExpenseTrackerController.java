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
    view.getTableModel().addRow(new Object[] { t.getAmount(), t.getCategory(), t.getTimestamp() });
    refresh();
    return true;
  }

  // Add filter method
  // Call model.applyFilter() and pass the filterType and filterConditionObject
  public void applyFilter(String filterType, Object filterConditionObject) {
    view.resetHighlight();
    List<Transaction> filteredTransactions = model.applyFilter(filterType, filterConditionObject);
    view.highlightFilteredRows(filteredTransactions, new java.awt.Color(173, 255,
        168));
  }
}