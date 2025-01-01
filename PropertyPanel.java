import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PropertyPanel extends JPanel {
    private PropertyManager propertyManager;
    private DefaultListModel<String> propertyListModel;
    private JList<String> propertyList;

    public PropertyPanel(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
        setLayout(new BorderLayout());

        add(new JLabel("Property Management"), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 2));

        centerPanel.add(new JLabel("Type:"));
        JTextField typeField = new JTextField();
        centerPanel.add(typeField);

        centerPanel.add(new JLabel("Size:"));
        JTextField sizeField = new JTextField();
        centerPanel.add(sizeField);

        centerPanel.add(new JLabel("Price:"));
        JTextField priceField = new JTextField();
        centerPanel.add(priceField);

        centerPanel.add(new JLabel("Location:"));
        JTextField locationField = new JTextField();
        centerPanel.add(locationField);

        centerPanel.add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField();
        centerPanel.add(descriptionField);

        propertyListModel = new DefaultListModel<>();
        propertyList = new JList<>(propertyListModel);
        JScrollPane scrollPane = new JScrollPane(propertyList);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Property List"), BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        add(listPanel, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Property");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = typeField.getText();
                double size = Double.parseDouble(sizeField.getText());
                double price = Double.parseDouble(priceField.getText());
                String location = locationField.getText();
                String description = descriptionField.getText();
                Property property = new Property(type, size, price, location, description);
                propertyManager.addProperty(property);
                updatePropertyList();
            }
        });
        buttonPanel.add(addButton);

        JButton modifyButton = new JButton("Modify Property");
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement modify property logic
            }
        });
        buttonPanel.add(modifyButton);

        JButton deleteButton = new JButton("Delete Property");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement delete property logic
            }
        });
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        updatePropertyList();
    }

    private void updatePropertyList() {
        propertyListModel.clear();
        List<Property> properties = propertyManager.getProperties();
        for (Property property : properties) {
            propertyListModel.addElement(property.getType() + " - " + property.getLocation());
        }
    }
}
