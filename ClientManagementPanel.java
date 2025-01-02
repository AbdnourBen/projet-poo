package hjk;

import javax.swing.*;
import java.awt.*;

public class ClientManagementPanel extends JPanel implements Observer {
    private DefaultListModel<Client> clientListModel = new DefaultListModel<>();
    private JList<Client> clientList;
    private RealEstateAgency agency;

    public ClientManagementPanel(RealEstateAgency agency) {
        this.agency = agency;
        setLayout(new BorderLayout());

        clientList = new JList<>(clientListModel);
        refreshClientList();

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JTextField txtName = new JTextField();
        JTextField txtContactInfo = new JTextField();
        String[] roles = {"Choose Client Role..", "Buyer", "Seller", "Leaser", "Renter"};
        JComboBox<String> cmbRole = new JComboBox<>(roles);
        cmbRole.setSelectedIndex(0); 
        JTextField txtPreferences = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Contact Info:"));
        inputPanel.add(txtContactInfo);
        inputPanel.add(new JLabel("Role:"));
        inputPanel.add(cmbRole);
        inputPanel.add(new JLabel("Preferences:"));
        inputPanel.add(txtPreferences);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton btnAdd = new JButton("Add Client");
        JButton btnDelete = new JButton("Delete Client");

        btnAdd.addActionListener(e -> {
            String name = txtName.getText();
            String contactInfo = txtContactInfo.getText();
            String role = (String) cmbRole.getSelectedItem();
            String preferences = txtPreferences.getText();
            if (!name.isEmpty() && !contactInfo.isEmpty() && !role.equals("Choose Client Role..") && !preferences.isEmpty()) {
                Client client = new Client(name, contactInfo, role, preferences);
                agency.addClient(client);
                refreshClientList();
                txtName.setText("");
                txtContactInfo.setText("");
                cmbRole.setSelectedIndex(0);
                txtPreferences.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedIndex = clientList.getSelectedIndex();
            if (selectedIndex >= 0) {
                Client selectedClient = clientListModel.get(selectedIndex);
                agency.deleteClient(selectedClient);
                refreshClientList();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a client to delete.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
        JButton btnModify = new JButton("Modify Client");
        btnModify.addActionListener(e -> {
            try {
                int selectedIndex = clientList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Client selectedClient = clientListModel.get(selectedIndex);

                    // Modify fields using dialog prompts
                    String newName = JOptionPane.showInputDialog(this, "Enter new name:", selectedClient.getName());
                    if (newName != null) selectedClient.setName(newName);

                    String newContactInfo = JOptionPane.showInputDialog(this, "Enter new contact info:", selectedClient.getContactInfo());
                    if (newContactInfo != null) selectedClient.setContactInfo(newContactInfo);

                    String newRole = JOptionPane.showInputDialog(this, "Enter new role:", selectedClient.getRole());
                    if (newRole != null) selectedClient.setRole(newRole);

                    String newPreferences = JOptionPane.showInputDialog(this, "Enter new preferences:", selectedClient.getPreferences());
                    if (newPreferences != null) selectedClient.setPreferences(newPreferences);

                    refreshClientList();
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a client to modify.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(btnModify);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(clientList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        agency.addObserver(this);
    }

    @Override
    public void update() {
        refreshClientList();
    }

    public void refreshClientList() {
        clientListModel.clear();
        for (Client client : agency.getClients()) {
            clientListModel.addElement(client);
        }
    }
}
