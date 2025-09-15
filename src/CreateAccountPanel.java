// 文件名: CreateAccountPanel.java
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CreateAccountPanel extends JPanel {

    private MainApplication mainApp;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public CreateAccountPanel(MainApplication mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 组件间距
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);
        JButton createButton = new JButton("确认创建");
        JButton backButton = new JButton("返回");

        JLabel titleLabel = new JLabel("创建新账户");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("用户名:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("密码:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("确认密码:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(confirmPasswordField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        backButton.addActionListener(e -> mainApp.showPanel("Welcome"));

        createButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            char[] confirmPasswordChars = confirmPasswordField.getPassword();
            String password = new String(passwordChars);
            String confirmPassword = new String(confirmPasswordChars);

            // 1. 输入验证
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "所有字段都不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "两次输入的密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DatabaseManager dbManager = mainApp.getDbManager();
            if (dbManager.userExists(username)) {
                JOptionPane.showMessageDialog(this, "该用户名已被注册！", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean success = dbManager.createAccount(username, password);
                if (success) {
                    JOptionPane.showMessageDialog(this, "账户创建成功！请登录。");
                    usernameField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    mainApp.showPanel("Welcome");
                } else {
                    JOptionPane.showMessageDialog(this, "创建账户失败", "数据库错误", JOptionPane.ERROR_MESSAGE);
                }
            }

            Arrays.fill(passwordChars, '0');
            Arrays.fill(confirmPasswordChars, '0');
        });
    }
}