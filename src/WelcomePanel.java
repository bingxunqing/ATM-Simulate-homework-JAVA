import javax.swing.*;
import java.awt.*;


public class WelcomePanel extends JPanel {
    private MainApplication mainApp;

    public WelcomePanel(MainApplication mainApp) {
        this.mainApp = mainApp;

        setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton createButton = new JButton("点我创建一个账户吧~");
        JButton loginButton = new JButton("点我登录你的账户吧！");

        Dimension buttonSize = new Dimension(150,50);
        createButton.setPreferredSize(buttonSize);
        loginButton.setPreferredSize(buttonSize);

        createButton.addActionListener(e -> {
//            JOptionPane.showMessageDialog(this,"正在努力跳转到创建账户页面噢");
        });

        loginButton.addActionListener(e -> {
//            JOptionPane.showMessageDialog(this,"正在努力跳转到登录页面噢");
            mainApp.showPanel("Login");
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,10,10);
        add(createButton,gbc);
        gbc.gridy = 1;
        add(loginButton,gbc);
    }

}
