import javax.swing.*;
import java.awt.*;

public class OperationsPanel extends JPanel {
    private JLabel welcomeLabel;
    private MainApplication mainApp;

    public OperationsPanel(MainApplication mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();
//        DatabaseManager dbManager = new DatabaseManager();
        String name = mainApp.getLoggedInUser();
        welcomeLabel = new JLabel("欢迎你呀，小奶龙"+name);
        welcomeLabel.setFont(new Font("宋体",Font.BOLD,22));

        Dimension buttonSize = new Dimension(200,40);
        JButton checkBalanceButton = new JButton("查询余额");
        JButton depositButton = new JButton("存款");
        JButton withdrawButton = new JButton("取款");
        JButton changePasswordButton = new JButton("修改密码");
        JButton logoutButton = new JButton("安全登出");

        checkBalanceButton.setPreferredSize(buttonSize);
        depositButton.setPreferredSize(buttonSize);
        withdrawButton.setPreferredSize(buttonSize);
        changePasswordButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);

        add(welcomeLabel, gbc);
        add(checkBalanceButton, gbc);
        add(depositButton, gbc);
        add(withdrawButton, gbc);
        add(changePasswordButton, gbc);
        add(logoutButton, gbc);

        checkBalanceButton.addActionListener(e -> {
            String currentUser = mainApp.getLoggedInUser();
            if(currentUser != null) {
                DatabaseManager dbManager = new DatabaseManager();
                double balance = dbManager.getBalance(currentUser);
                String balanceMessage = String.format("小奶龙，你的账户现在只有 %.2f 元了哦",balance);
                JOptionPane.showMessageDialog(this, balanceMessage,"余额查询",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        logoutButton.addActionListener(e -> {
            mainApp.setLoggedInUser(null);
            mainApp.setLoggedInUser("welcome");
        });

        depositButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,"存款");
        });

        withdrawButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,"取款");
        });

        changePasswordButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,"修改密码");
        });

        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,"退出");
        });
    }

    public void updateWelcomeMessage(){
        String currentUser = mainApp.getLoggedInUser();
        if(currentUser != null) {
            welcomeLabel.setText("欢迎您呀，小奶龙"+currentUser);
        }
    }


}
