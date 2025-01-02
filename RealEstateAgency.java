package hjk;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RealEstateAgency {
    private ArrayList<Property> properties;
    private ArrayList<Client> clients;
    private ArrayList<Transaction> transactions;
    private ArrayList<Agent> agents;
    private List<Observer> observers = new ArrayList<>();

    public RealEstateAgency() {
        properties = new ArrayList<>();
        clients = new ArrayList<>();
        transactions = new ArrayList<>();
        agents = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    // Property Management
    public void addProperty(Property property) {
        properties.add(property);
        notifyObservers();
    }

    public void deleteProperty(Property selectedProperty) {
        if (properties.contains(selectedProperty)) {
            properties.remove(selectedProperty);
            notifyObservers();
        }
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    // Agent Management
    public void addAgent(Agent agent) {
        agents.add(agent);
        notifyObservers();
    }

    public void deleteAgent(Agent agent) {
        if (agents.contains(agent)) {
            agents.remove(agent);
            notifyObservers();
        } else {
            System.out.println("Agent not found.");
        }
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    // Client Management
    public void addClient(Client client) {
        clients.add(client);
        notifyObservers();
    }

    public void deleteClient(Client client) {
        if (clients.contains(client)) {
            clients.remove(client);
            notifyObservers();
        } else {
            System.out.println("Client not found.");
        }
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    // Transaction Management
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        notifyObservers();
    }

    public void deleteTransaction(Transaction transaction) {
        if (transactions.contains(transaction)) {
            transactions.remove(transaction);
            notifyObservers();
        } else {
            System.out.println("Transaction not found.");
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    // Save and Load Data
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("agency_data.dat"))) {
            oos.writeObject(properties);
            oos.writeObject(clients);
            oos.writeObject(transactions);
            oos.writeObject(agents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("agency_data.dat"))) {
            properties = (ArrayList<Property>) ois.readObject();
            clients = (ArrayList<Client>) ois.readObject();
            transactions = (ArrayList<Transaction>) ois.readObject();
            agents = (ArrayList<Agent>) ois.readObject();
            notifyObservers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
