import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientPanel extends JPanel {
    private ClientManager clientManager;
    private DefaultListModel<String> clientListModel;
    private JList<String> clientList;

    public ClientPanel(ClientManager clientManager) {
        this.clientManager = clientManager;
        setLayout(new BorderLayout());

        add(new JLabel("Client Management"), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2));

        centerPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        centerPanel.add(nameField);

        centerPanel.add(new JLabel("Contact Info:"));
        JTextField contactField = new JTextField();
        centerPanel.add(contactField);

        centerPanel.add(new JLabel("Preferences:"));
        JTextField preferencesField = new JTextField();
        centerPanel.add(preferencesField);

        clientListModel = new DefaultListModel<>();
        clientList = new JList<>(clientListModel);
        JScrollPane scrollPane = new JScrollPane(clientList);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Client List"), BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        add(listPanel, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Client");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String contact = contactField.getText();
                String preferences = preferencesField.getText();
                Client client = new Client(name, contact, preferences);
                clientManager.addClient(client);
                updateClientList();
            }
        });
        buttonPanel.add(addButton);

        JButton modifyButton = new JButton("Modify Client");
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String contact = contactField.getText();
                String preferences = preferencesField.getText();
                int index = -1;
                for (int i = 0; i < clientManager.getClients().size(); i++) {
                    if (clientManager.getClients().get(i).getName().equals(name)) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    Client newClient = new Client(name, contact, preferences);
                    clientManager.modifyClient(index, newClient);
                    updateClientList();
                } else {
                    JOptionPane.showMessageDialog(null, "Client not found");
                }
            }
        });
        buttonPanel.add(modifyButton);

        JButton deleteButton = new JButton("Delete Client");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int index = -1;
                for (int i = 0; i < clientManager.getClients().size(); i++) {
                    if (clientManager.getClients().get(i).getName().equals(name)) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    clientManager.deleteClient(index);
                    updateClientList();
                } else {
                    JOptionPane.showMessageDialog(null, "Client not found");
                }
            }
        });
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        updateClientList();
    }

    private void updateClientList() {
        clientListModel.clear();
        List<Client> clients = clientManager.getClients();
        for (Client client : clients) {
            clientListModel.addElement(client.getName());
        }
    }
}
