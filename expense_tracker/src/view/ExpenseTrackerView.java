package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import controller.TransactionFilterInterface;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JButton filterTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JTextField categoryFilterField;
  private JFormattedTextField amountFilterField;
  private TransactionFilterInterface filter;

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");
    filterTransactionBtn = new JButton("Filter Transactions");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    JLabel categoryFilterLabel = new JLabel("Category Filter:");
    categoryFilterField = new JTextField(10);

    JLabel amountFilterLabel = new JLabel("Amount Filter:");
    amountFilterField = new JFormattedTextField(format);
    amountFilterField.setColumns(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
    inputPanel.add(categoryFilterLabel);
    inputPanel.add(categoryFilterField);
    inputPanel.add(amountFilterLabel);
    inputPanel.add(amountFilterField);
    inputPanel.add(filterTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    buttonPanel.add(filterTransactionBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  

  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public String getCategoryFilterField() {
    return categoryFilterField.getText();
  }

  public void setCategoryFilterField(JTextField categoryFilterField) {
    this.categoryFilterField = categoryFilterField;
  }

  public double getAmountFilterField() {
    if(amountFilterField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountFilterField.getText());
    return amount;
    }
  }

  public void setAmountFilterField(JFormattedTextField amountFilterField) {
    this.amountFilterField = amountFilterField;
  }

  public JButton getFilterTransactionBtn() {
    return filterTransactionBtn;
  }

  public void setFilter(TransactionFilterInterface filter) {
    this.filter = filter;
  }

  public TransactionFilterInterface getFilter() {
    return filter;
  }

  public void highlightFilteredRows(List<Transaction> filteredTransactions, Color color) {
    // Clear the table selection
    transactionsTable.clearSelection();

    // Highlight the filtered rows
    DefaultTableModel tableModel = (DefaultTableModel) transactionsTable.getModel();
    for (int i = 0; i < tableModel.getRowCount(); i++) {
      System.out.println("tableModel.getRowCount() " + tableModel.getRowCount());
      Transaction t = (Transaction) tableModel.getValueAt(i, 0);
      if (t.getAmount() == filteredTransactions.get(i).getAmount() && t.getCategory().equals(filteredTransactions.get(i).getCategory())) {
        transactionsTable.addRowSelectionInterval(i, i);
        transactionsTable.setSelectionBackground(color);
      }
    }
  }
}
