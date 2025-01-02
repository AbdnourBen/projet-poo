package hjk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PropertyManagementPanel extends JPanel implements Observer {
    private DefaultListModel<Property> propertyListModel = new DefaultListModel<>();
    private JList<Property> propertyList;
    private RealEstateAgency agency;
    private JComboBox<String> cmbType;
    private JComboBox<String> cmbAgent;
    private JTextField txtPrice;
    private JTextField txtLocation;
    private JTextField txtSize;
    private JTextField txtDescription;
    private static PropertyManagementPanel instance;

    public PropertyManagementPanel(RealEstateAgency agency) {
        this.agency = agency;
        setLayout(new BorderLayout());

        propertyList = new JList<>(propertyListModel);
        refreshPropertyList();

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        String[] propertyTypes = {"Choose Type..", "Residential Property", "Commercial Property", "Industrial Property", "Land Property"};
        cmbType = new JComboBox<>(propertyTypes);
        if (cmbType.getItemCount() > 0) {
            cmbType.setSelectedIndex(0); 
        }

        agency.addObserver(this);
        cmbAgent = new JComboBox<>(getAgentNames());
        if (cmbAgent.getItemCount() > 0) {
            cmbAgent.setSelectedIndex(0); 
        }

        txtPrice = new JTextField();
        txtLocation = new JTextField();
        txtSize = new JTextField();
        txtDescription = new JTextField();

        inputPanel.add(new JLabel("Type:"));
        inputPanel.add(cmbType);
        inputPanel.add(new JLabel("Assigned Agent:"));
        inputPanel.add(cmbAgent);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(txtPrice);
        inputPanel.add(new JLabel("Location:"));
        inputPanel.add(txtLocation);
        inputPanel.add(new JLabel("Size:"));
        inputPanel.add(txtSize);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(txtDescription);

        JButton btnAddProperty = new JButton("Add Property");
        btnAddProperty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String type = (String) cmbType.getSelectedItem();
                    String agent = (String) cmbAgent.getSelectedItem();
                    String location = txtLocation.getText();
                    double price = Double.parseDouble(txtPrice.getText());
                    double size = Double.parseDouble(txtSize.getText());
                    String description = txtDescription.getText();
                    if (type.equals("Choose Type..")) {
                        JOptionPane.showMessageDialog(null, "Choose a Property Type from the dropdown menu!");
                    } else if (agent.equals("Choose Agent..")) {
                        JOptionPane.showMessageDialog(null, "Choose an Agent from the dropdown menu!");
                    } else if (location.isEmpty() || txtPrice.getText().isEmpty() || txtSize.getText().isEmpty() || description.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "All fields must be filled!");
                    } else {
                        Property property = new Property(type, location, size, price, description, agent);
                        agency.addProperty(property);
                        refreshPropertyList();

                        
                        if (cmbType.getItemCount() > 0) {
                            cmbType.setSelectedIndex(0);
                        }
                        if (cmbAgent.getItemCount() > 0) {
                            cmbAgent.setSelectedIndex(0);
                        }
                        txtLocation.setText("");
                        txtPrice.setText("");
                        txtSize.setText("");
                        txtDescription.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for price and size.");
                }
            }
        });

        JButton btnDeleteProperty = new JButton("Delete Property");
        btnDeleteProperty.addActionListener(e -> {
            try {
                int selectedIndex = propertyList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Property selectedProperty = propertyListModel.get(selectedIndex);
                    agency.deleteProperty(selectedProperty);
                    refreshPropertyList();
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a property to delete.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred while deleting the property.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton btnModify = new JButton("Modify Property");
        btnModify.addActionListener(e -> {
            try {
            	 String type = (String) cmbType.getSelectedItem();
                 String agent = (String) cmbAgent.getSelectedItem();
                int selectedIndex = propertyList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Property selectedProperty = propertyListModel.get(selectedIndex);

                    // Update the selected property's details
                    
                    selectedProperty.setLocation(txtLocation.getText());
                    selectedProperty.setPrice(Double.parseDouble(txtPrice.getText()));
                    selectedProperty.setSize(Double.parseDouble(txtSize.getText()));
                    selectedProperty.setDescription(txtDescription.getText());
                    selectedProperty.setAssignedAgent((String) cmbAgent.getSelectedItem());
                    selectedProperty.setType((String) cmbType.getSelectedItem());
                    
                    refreshPropertyList();

                    
                    txtLocation.setText("");
                    txtPrice.setText("");
                    txtSize.setText("");
                    txtDescription.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a property to modify.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Price and Size must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(btnModify);
        buttonPanel.add(btnAddProperty);
        buttonPanel.add(btnDeleteProperty);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(propertyList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        instance = this;
    }

    public static PropertyManagementPanel getInstance() {
        return instance;
    }

    public static void setInstance(PropertyManagementPanel panel) {
        instance = panel;
    }

    private String[] getAgentNames() {
        String[] agents = new String[agency.getAgents().size() + 1];
        agents[0] = "Choose Agent..";
        for (int i = 0; i < agency.getAgents().size(); i++) {
            agents[i + 1] = agency.getAgents().get(i).getName();
        }
        return agents;
    }

    private void refreshPropertyList() {
        propertyListModel.clear();
        for (Property property : agency.getProperties()) {
            propertyListModel.addElement(property);
        }
    }

    @Override
    public void update() {
        cmbAgent.setModel(new DefaultComboBoxModel<>(getAgentNames()));
        refreshPropertyList();
    }
}
