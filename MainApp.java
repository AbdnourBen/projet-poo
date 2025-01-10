package hjk;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        RealEstateAgency agency = new RealEstateAgency();
        agency.loadData(); 

        JFrame frame = new JFrame("Real Estate Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        

        JTabbedPane tabbedPane = new JTabbedPane();
        PropertyManagementPanel propertyPanel = new PropertyManagementPanel(agency);
        PropertyManagementPanel.setInstance(propertyPanel); 
        AgentManagementPanel agentPanel = new AgentManagementPanel(agency);
        ClientManagementPanel clientPanel = new ClientManagementPanel(agency);
        TransactionManagementPanel transactionPanel = new TransactionManagementPanel(agency);
        AppointmentManagementPanel appointmentPanel = new AppointmentManagementPanel(agency);

        tabbedPane.add("Properties", propertyPanel);
        tabbedPane.add("Agents", agentPanel);
        tabbedPane.add("Clients", clientPanel);
        tabbedPane.add("Transactions", transactionPanel);
        tabbedPane.add("Appointments", appointmentPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(agency::saveData));
    }
}
