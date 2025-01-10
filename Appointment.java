package hjk;



import java.util.Date;

public class Appointment {
    private Agent agent;
    private Client client;
    private Date date;

    public Appointment(Agent agent, Client client, Date date) {
        this.agent = agent;
        this.client = client;
        this.date = date;
    }

    // Getters and setters
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}