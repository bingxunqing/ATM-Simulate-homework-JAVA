import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LoginPanel extends JPanel {

    private MainApplication mainApp;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(MainApplication mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("登录");
        JButton backButton = new JButton("返回");

        // 标题
        JLabel titleLabel = new JLabel("用户登录");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // 恢复布局默认值
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        // 用户名行
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("用户名:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        // 密码行
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("密码:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        backButton.addActionListener(e -> mainApp.showPanel("welcome"));

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // 1. 输入验证
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "用户名和密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. 调用 DatabaseManager 进行登录验证
            DatabaseManager dbManager = mainApp.getDbManager();
            boolean loginSuccess = dbManager.login(username, password);

            // 3. 根据登录结果进行操作
            if (loginSuccess) {
                // 登录成功！
                mainApp.setLoggedInUser(username); // 在主程序中记录下当前登录的用户名
                JOptionPane.showMessageDialog(this, "登录成功！欢迎你, " + username);

                usernameField.setText("");
                passwordField.setText("");

                mainApp.getOperationsPanel().updateWelcomeMessage();

                mainApp.showPanel("Operations");

            } else {
                // 登录失败
                JOptionPane.showMessageDialog(this, "用户名或密码错误！", "登录失败", JOptionPane.ERROR_MESSAGE);
            }

            // 4. 清理密码字符数组（安全实践）
            Arrays.fill(passwordChars, '0');
        });
    }
}
