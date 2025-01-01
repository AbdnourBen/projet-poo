import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private List<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        clients.add(client);
    }

    public void modifyClient(int index, Client newClient) {
        if (index >= 0 && index < clients.size()) {
            clients.set(index, newClient);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void deleteClient(int index) {
        if (index >= 0 && index < clients.size()) {
            clients.remove(index);
        } else {
            System.out.println("Invalid index");
        }
    }
}
