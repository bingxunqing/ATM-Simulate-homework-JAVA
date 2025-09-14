import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class ChangePasswordPanel extends JPanel{
    private MainApplication mainApp;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;

    public ChangePasswordPanel(MainApplication mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        newPasswordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);
        JButton confirmButton = new JButton("确认修改");
        JButton backButton = new JButton("返回");

        JLabel titleLabel = new JLabel("修改密码");
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
        add(new JLabel("新密码:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(newPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("确认新密码:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(confirmPasswordField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        backButton.addActionListener(e -> {
            mainApp.showPanel("Operations");
        });
        confirmButton.addActionListener(e -> {
            handleChangePassword();
        });

    }
    public boolean handleChangePassword(){
        char[] password = newPasswordField.getPassword();
        char[] confirmPassword = confirmPasswordField.getPassword();
        String newPassword = new String(password);
        String confirmNewPassword = new String(confirmPassword);

        if(newPassword.isEmpty() ||  confirmNewPassword.isEmpty()){
            JOptionPane.showMessageDialog(this,"还有东西没输完噢","错误",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!newPassword.equals(confirmNewPassword)){
            JOptionPane.showMessageDialog(this,"两次密码不一样","错误",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        String currentUser = mainApp.getLoggedInUser();
        DatabaseManager dbManager = mainApp.getDbManager();
        boolean success = dbManager.updatePassword(currentUser, newPassword);

        Arrays.fill(password, '0');
        Arrays.fill(confirmPassword, '0');
        clearFields();

        if(success){
            JOptionPane.showMessageDialog(this,"密码修改成功！\n请使用新密码登录","成功",JOptionPane.INFORMATION_MESSAGE);
            mainApp.setLoggedInUser(null);
            mainApp.showPanel("Login");
        }else{
            JOptionPane.showMessageDialog(this,"密码修改失败","数据库发生了小差错",JOptionPane.WARNING_MESSAGE);
        }

        return success;
    }

    public void clearFields(){
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }

}
