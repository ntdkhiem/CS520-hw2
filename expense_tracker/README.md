# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction.

## Compile

To compile the code from terminal, use the following command:

```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation.

## Java Version

This code is compiled with `openjdk 17.0.7 2023-04-18`. Please update your JDK accordingly if you face any incompatibility issue.

## Features

- Input validation for both amount and category.
  - $0 < amount \le 1000$
  - $category \in \text{[food, travel, bills, entertainment, other]}$
- Add/Remove a transaction.
- Each added transaction will have an associated timestamp.

## New Features

- **Filter Transactions**: You can now filter transactions based on their category or amount. To use this feature, simply select the desired filter type and enter the filter condition. The transactions that match the filter will be highlighted in the transactions table.

- **Reset Highlights**: After applying a filter, you can reset the highlighted rows to clear the filter. To do this, simply click the 'Reset Highlights' button.

- **Improved Transaction Adding**: Each added transaction now automatically gets an associated timestamp. This allows you to easily track when each transaction was added.

- **Transaction Removal**: You can now remove transactions from the list. To remove a transaction, select it in the transactions table and click the 'Remove Transaction' button.
