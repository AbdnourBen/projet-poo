package hjk;

import javax.swing.*;
import java.awt.*;


public class TransactionManagementPanel extends JPanel implements Observer {
    private DefaultListModel<Transaction> transactionListModel = new DefaultListModel<>();
    private JList<Transaction> transactionList;
    private RealEstateAgency agency;
    private JComboBox<Property> cmbProperty;
    private JComboBox<Client> cmbClient;
    private JComboBox<String> cmbTransactionType;

    public TransactionManagementPanel(RealEstateAgency agency) {
        this.agency = agency;
        setLayout(new BorderLayout());

        transactionList = new JList<>(transactionListModel);
        refreshTransactionList();

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        String[] transactionTypes = {"Choose Transaction Type..", "Purchase", "Sale", "Lease", "Rent"};
        cmbTransactionType = new JComboBox<>(transactionTypes);
        cmbTransactionType.setSelectedIndex(0); 
        cmbProperty = new JComboBox<>(getPropertyArray());
        cmbProperty.setSelectedIndex(0); 
        
        
        cmbClient = new JComboBox<>(getClientArray());
        cmbClient.setSelectedIndex(0); 
        
        JTextField txtPaymentAmount = new JTextField();
        JTextField txtDateOfTransaction = new JTextField();

        inputPanel.add(new JLabel("Transaction Type:"));
        inputPanel.add(cmbTransactionType);
        inputPanel.add(new JLabel("Property:"));
        inputPanel.add(cmbProperty);
        inputPanel.add(new JLabel("Client:"));
        inputPanel.add(cmbClient);
        inputPanel.add(new JLabel("Payment Amount:"));
        inputPanel.add(txtPaymentAmount);
        inputPanel.add(new JLabel("Date of Transaction:"));
        inputPanel.add(txtDateOfTransaction);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton btnAdd = new JButton("Add Transaction");
        JButton btnDelete = new JButton("Delete Transaction");

        btnAdd.addActionListener(e -> {
            String transactionType = (String) cmbTransactionType.getSelectedItem();
            Property property = (Property) cmbProperty.getSelectedItem();
            Client client = (Client) cmbClient.getSelectedItem();
            String paymentAmount = txtPaymentAmount.getText();
            String dateOfTransaction = txtDateOfTransaction.getText();

            if (transactionType.equals("Choose Transaction Type..") || property == null || client == null || paymentAmount.isEmpty() || dateOfTransaction.isEmpty()) {
                JOptionPane.showMessageDialog(TransactionManagementPanel.this, "Please fill in all fields.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                Transaction transaction = new Transaction(transactionType, property, client, Double.parseDouble(paymentAmount), dateOfTransaction);
                agency.addTransaction(transaction);
                refreshTransactionList();
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedIndex = transactionList.getSelectedIndex();
            if (selectedIndex >= 0) {
                Transaction selectedTransaction = transactionListModel.get(selectedIndex);
                agency.deleteTransaction(selectedTransaction);
                refreshTransactionList();
            } else {
                JOptionPane.showMessageDialog(TransactionManagementPanel.this, "Please select a transaction to delete.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(transactionList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        agency.addObserver(this);
    }

    private Client[] getClientArray() {
        Client[] clients = new Client[agency.getClients().size() + 1];
        clients[0] = new Client("Choose Client..", "", "", "");
        for (int i = 0; i < agency.getClients().size(); i++) {
            clients[i + 1] = agency.getClients().get(i);
        }
        return clients;
    }

    private Property[] getPropertyArray() {
        Property[] properties = new Property[agency.getProperties().size() + 1];
        properties[0] = new Property("Choose Property..", "", 0, 0, "", "");
        for (int i = 0; i < agency.getProperties().size(); i++) {
            properties[i + 1] = agency.getProperties().get(i);
        }
        return properties;
    }

    @Override
    public void update() {
        cmbProperty.setModel(new DefaultComboBoxModel<>(getPropertyArray()));
        cmbClient.setModel(new DefaultComboBoxModel<>(getClientArray()));
    }

    private void refreshTransactionList() {
        transactionListModel.clear();
        for (Transaction transaction : agency.getTransactions()) {
            transactionListModel.addElement(transaction);
        }
    }
}
