import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Real Estate Agency Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create tabs
        JTabbedPane tabbedPane = new JTabbedPane();
       tabbedPane.addTab("Properties", new PropertyPanel(null));
        tabbedPane.addTab("Clients", new ClientPanel(null));
        tabbedPane.addTab("Transactions", new TransactionPanel(null));

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
