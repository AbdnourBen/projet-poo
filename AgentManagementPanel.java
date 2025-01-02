package hjk;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AgentManagementPanel extends JPanel {
    private DefaultListModel<Agent> agentListModel = new DefaultListModel<>();
    private JList<Agent> agentList;
    private RealEstateAgency agency;

    public AgentManagementPanel(RealEstateAgency agency) {
        this.agency = agency;
        setLayout(new BorderLayout());

        agentList = new JList<>(agentListModel);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JTextField txtName = new JTextField();
        JTextField txtId = new JTextField();
        JTextField txtContactInfo = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(txtId);
        inputPanel.add(new JLabel("Contact Info:"));
        inputPanel.add(txtContactInfo);

        JButton btnAdd = new JButton("Add Agent");
        btnAdd.addActionListener(e -> {
            String name = txtName.getText();
            String id = txtId.getText();
            String contactInfo = txtContactInfo.getText();
            if (!name.isEmpty() && !id.isEmpty() && !contactInfo.isEmpty()) {
                Agent agent = new Agent(name, id, contactInfo);
                agency.addAgent(agent);
                refreshAgentList();
                txtName.setText("");
                txtId.setText("");
                txtContactInfo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "All fields are mandatory!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnDelete = new JButton("Delete Agent");
        btnDelete.addActionListener(e -> {
            int selectedIndex = agentList.getSelectedIndex();
            if (selectedIndex >= 0) {
                Agent selectedAgent = agentListModel.get(selectedIndex);
                agency.deleteAgent(selectedAgent);
                refreshAgentList();
                PropertyManagementPanel.getInstance().update();
            } else {
                JOptionPane.showMessageDialog(this, "Please select an agent to delete.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);

        add(new JScrollPane(agentList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        refreshAgentList();
    }

    private void refreshAgentList() {
        agentListModel.clear();
        ArrayList<Agent> agents = agency.getAgents();
        for (Agent agent : agents) {
            agentListModel.addElement(agent);
        }
    }
}
