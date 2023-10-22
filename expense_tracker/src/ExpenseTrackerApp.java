import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

public class ExpenseTrackerApp {

  public static void main(String[] args) {

    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();

      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);

      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // Handle category filter button clicks
    view.getFilterTransactionBtn().addActionListener(e -> {
      // Get filter options from user
      String[] options = { "Amount", "Category" };
      int choice = JOptionPane.showOptionDialog(view, "Filter by:", "Filter Options", JOptionPane.DEFAULT_OPTION,
          JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

      // Get filter data from view
      double amount = 0;
      String category = "";
      switch (choice) {
        case 0:
          amount = Double.parseDouble(JOptionPane.showInputDialog(view, "Enter amount to filter by:"));
          // Call controller to apply filter
          controller.applyFilter("amount", amount);
          break;
        case 1:
          category = JOptionPane.showInputDialog(view, "Enter category to filter by:");
          // Call controller to apply filter
          controller.applyFilter("category", category);
          break;
        default:
          break;
      }
    });

  }

}