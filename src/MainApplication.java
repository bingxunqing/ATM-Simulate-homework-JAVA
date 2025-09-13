import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class MainApplication {

    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private DatabaseManager dbManager;
    private String loggedInUser=null;
    private OperationsPanel operationsPanel;

    public MainApplication() {
        dbManager = new DatabaseManager();
        frame = new JFrame("奶农银行hh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500,500);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        WelcomePanel welcomePanel = new WelcomePanel(this);
        CreateAccountPanel createAccountPanel = new CreateAccountPanel(this);
        LoginPanel loginPanel = new LoginPanel(this);
        operationsPanel = new OperationsPanel(this);

        mainPanel.add(welcomePanel,"Welcome");
        mainPanel.add(createAccountPanel,"CreateAccount");
        mainPanel.add(loginPanel,"Login");
        mainPanel.add(operationsPanel,"Operations");

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showPanel(String panelName){
        cardLayout.show(mainPanel,panelName);
    }

    public DatabaseManager getDbManager() {
        return dbManager;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(MainApplication::new);
    }

    public OperationsPanel getOperationsPanel() {
        return operationsPanel;
    }
}

