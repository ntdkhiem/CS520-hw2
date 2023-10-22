
// package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;

public class TestExample {

    private ExpenseTrackerModel model;
    private ExpenseTrackerView view;
    private ExpenseTrackerController controller;

    @Before
    public void setup() {
        model = new ExpenseTrackerModel();
        view = new ExpenseTrackerView();
        controller = new ExpenseTrackerController(model, view);
    }

    public double getTotalCost() {
        double totalCost = 0.0;
        List<Transaction> allTransactions = model.getTransactions(); // Using the model's getTransactions method
        for (Transaction transaction : allTransactions) {
            totalCost += transaction.getAmount();
        }
        return totalCost;
    }

    @Test
    public void testAddTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());

        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(50.00, "food"));

        // Post-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());

        // Check the contents of the list
        assertEquals(50.00, getTotalCost(), 0.01);
    }

    @Test
    public void testRemoveTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());

        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "Groceries");
        model.addTransaction(addedTransaction);

        // Pre-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());

        // Perform the action: Remove the transaction
        model.removeTransaction(addedTransaction);

        // Post-condition: List of transactions is empty
        List<Transaction> transactions = model.getTransactions();
        assertEquals(0, transactions.size());

        // Check the total cost after removing the transaction
        double totalCost = getTotalCost();
        assertEquals(0.00, totalCost, 0.01);
    }

    @Test
    public void testAmountFilter() {
        // Add some transactions
        controller.addTransaction(50.00, "food");
        controller.addTransaction(100.00, "rent");
        controller.addTransaction(150.00, "utilities");

        // Apply the amount filter
        controller.applyFilter("amount", 100.00);

        // Check that the correct transactions are highlighted
        List<Transaction> highlightedTransactions = view.getHighlightedTransactions();
        assertEquals(2, highlightedTransactions.size());
        for (Transaction transaction : highlightedTransactions) {
            assertTrue(transaction.getAmount() >= 100.00);
        }
    }

    @Test
    public void testCategoryFilter() {
        // Add some transactions
        controller.addTransaction(50.00, "food");
        controller.addTransaction(100.00, "rent");
        controller.addTransaction(150.00, "utilities");

        // Apply the category filter
        controller.applyFilter("category", "food");

        // Check that the correct transactions are highlighted
        List<Transaction> highlightedTransactions = view.getHighlightedTransactions();
        assertEquals(1, highlightedTransactions.size());
        for (Transaction transaction : highlightedTransactions) {
            assertEquals("food", transaction.getCategory());
        }
    }

    @Test
    public void testResetHighlight() {
        // Add some transactions
        controller.addTransaction(50.00, "food");
        controller.addTransaction(100.00, "rent");
        controller.addTransaction(150.00, "utilities");

        // Apply the category filter
        controller.applyFilter("category", "food");

        // Reset the highlights
        controller.resetHighlight();

        // Check that no transactions are highlighted
        List<Transaction> highlightedTransactions = view.getHighlightedTransactions();
        assertEquals(0, highlightedTransactions.size());
    }

}