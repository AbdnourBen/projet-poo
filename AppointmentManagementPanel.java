package hjk;


import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class AppointmentManagementPanel extends JPanel {
    private RealEstateAgency agency;

    public AppointmentManagementPanel(RealEstateAgency agency) {
        this.agency = agency;
        setLayout(new BorderLayout());

        // Add components and layout for managing appointments
        JButton addButton = new JButton("Add Appointment");
        JButton modifyButton = new JButton("Modify Appointment");
        JButton deleteButton = new JButton("Delete Appointment");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.NORTH);

        // Add action listeners for buttons (implement the actions as needed)
        addButton.addActionListener(e -> addAppointment());
        modifyButton.addActionListener(e -> modifyAppointment());
        deleteButton.addActionListener(e -> deleteAppointment());
    }

    private void addAppointment() {
        Agent agent = selectAgent();
        Client client = selectClient();
        Date date = selectDate();
        if (agent != null && client != null && date != null) {
            Appointment appointment = new Appointment(agent, client, date);
            agency.addAppointment(appointment);
            JOptionPane.showMessageDialog(this, "Appointment added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Appointment creation failed. Ensure all fields are selected.");
        }
    }

    private void modifyAppointment() {
        Appointment oldAppointment = selectAppointment();
        if (oldAppointment != null) {
            Agent agent = selectAgent();
            Client client = selectClient();
            Date date = selectDate();
            if (agent != null && client != null && date != null) {
                Appointment newAppointment = new Appointment(agent, client, date);
                agency.modifyAppointment(oldAppointment, newAppointment);
                JOptionPane.showMessageDialog(this, "Appointment modified successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Appointment modification failed. Ensure all fields are selected.");
            }
        }
    }

    private void deleteAppointment() {
        Appointment appointment = selectAppointment();
        if (appointment != null) {
            agency.deleteAppointment(appointment);
            JOptionPane.showMessageDialog(this, "Appointment deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "No appointment selected for deletion.");
        }
    }

    private Agent selectAgent() {
        List<Agent> agents = agency.getAgents();
        if (agents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No agents available.");
            return null;
        }
        Agent[] agentArray = agents.toArray(new Agent[0]);
        return (Agent) JOptionPane.showInputDialog(this, "Select an Agent:", "Select Agent",
                JOptionPane.QUESTION_MESSAGE, null, agentArray, agentArray[0]);
    }

    private Client selectClient() {
        List<Client> clients = agency.getClients();
        if (clients.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No clients available.");
            return null;
        }
        Client[] clientArray = clients.toArray(new Client[0]);
        return (Client) JOptionPane.showInputDialog(this, "Select a Client:", "Select Client",
                JOptionPane.QUESTION_MESSAGE, null, clientArray, clientArray[0]);
    }

    private Date selectDate() {
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JComponent editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy HH:mm");
        dateSpinner.setEditor(editor);
        int option = JOptionPane.showOptionDialog(this, dateSpinner, "Select Date and Time",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) {
            return (Date) dateSpinner.getValue();
        }
        return null;
    }

    private Appointment selectAppointment() {
        List<Appointment> appointments = agency.getAppointments();
        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No appointments available.");
            return null;
        }
        Appointment[] appointmentArray = appointments.toArray(new Appointment[0]);
        return (Appointment) JOptionPane.showInputDialog(this, "Select an Appointment:", "Select Appointment",
                JOptionPane.QUESTION_MESSAGE, null, appointmentArray, appointmentArray[0]);
    }
}