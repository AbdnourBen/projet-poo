import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TransactionPanel extends JPanel {
    private TransactionManager transactionManager;
    private DefaultListModel<String> transactionListModel;
    private JList<String> transactionList;

    public TransactionPanel(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        setLayout(new BorderLayout());

        add(new JLabel("Transaction Management"), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 2));

        centerPanel.add(new JLabel("Property:"));
        JTextField propertyField = new JTextField();
        centerPanel.add(propertyField);

        centerPanel.add(new JLabel("Client:"));
        JTextField clientField = new JTextField();
        centerPanel.add(clientField);

        centerPanel.add(new JLabel("Date:"));
        JTextField dateField = new JTextField();
        centerPanel.add(dateField);

        centerPanel.add(new JLabel("Type:"));
        JTextField typeField = new JTextField();
        centerPanel.add(typeField);

        centerPanel.add(new JLabel("Amount:"));
        JTextField amountField = new JTextField();
        centerPanel.add(amountField);

        transactionListModel = new DefaultListModel<>();
        transactionList = new JList<>(transactionListModel);
        JScrollPane scrollPane = new JScrollPane(transactionList);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Transaction List"), BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        add(listPanel, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Transaction");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement add transaction logic
                updateTransactionList();
            }
        });
        buttonPanel.add(addButton);

        JButton modifyButton = new JButton("Modify Transaction");
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement modify transaction logic
                updateTransactionList();
            }
        });
        buttonPanel.add(modifyButton);

        JButton deleteButton = new JButton("Delete Transaction");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement delete transaction logic
                updateTransactionList();
            }
        });
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        updateTransactionList();
    }

    private void updateTransactionList() {
        transactionListModel.clear();
        List<Transaction> transactions = transactionManager.getTransactions();
        for (Transaction transaction : transactions) {
            transactionListModel.addElement(transaction.getProperty() + " - " + transaction.getClient());
        }
    }
}
