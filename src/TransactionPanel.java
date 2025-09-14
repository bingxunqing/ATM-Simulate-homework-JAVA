import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class TransactionPanel extends JPanel {

    MainApplication mainApp;
    String transactionType;
    private JLabel titleLabel;
    private JTextField amountField;
    private JButton confirmButton;
    private JButton backButton;

    public TransactionPanel(MainApplication mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("请输入您需要操作的金额");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 22));
        amountField = new JTextField(15);
        confirmButton = new JButton("确认");
        backButton = new JButton("返回");
        gbc.gridx = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new JLabel("金额:"), gbc);

        gbc.gridx = 1;
        add(amountField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);
        add(buttonPanel, gbc);

        backButton.addActionListener(e -> {
            amountField.setText("");
            mainApp.showPanel("Operations");
        });

        confirmButton.addActionListener(e -> {
            handleTransaction();
        });

    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        if(Objects.equals(transactionType,"deposit")){
            titleLabel.setText("存款中...");
        }else if(Objects.equals(transactionType,"withdraw")){
            titleLabel.setText("取款中");
        }
        amountField.setText("");
    }

    public void handleTransaction(){
        String amountS = amountField.getText();
        Double amount ;
        try{
            amount = Double.parseDouble(amountS);
            if(amount < 0){
                JOptionPane.showMessageDialog(this,"输啥呢？");
                return;
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"输啥呢？");
            return;
        }

        String currentUser = mainApp.getLoggedInUser();
        DatabaseManager dbManager = mainApp.getDbManager();
        double CurBalance = dbManager.getBalance(currentUser);
        double newBalance=0.0;

        if(Objects.equals(transactionType,"deposit")){
            newBalance = amount + CurBalance;
        }else if(Objects.equals(transactionType,"withdraw")){
            if(amount > CurBalance){
                JOptionPane.showMessageDialog(this,"你有几个子?");
                return;
            }
            newBalance = CurBalance - amount ;
        }

        boolean success = dbManager.updateBalance(currentUser,newBalance);

        if(success){
            String successMsg = String.format("操作成功！\n 当前余额为：%.2f",newBalance);
            JOptionPane.showMessageDialog(this,successMsg,"成功",JOptionPane.INFORMATION_MESSAGE);
            mainApp.showPanel("Operations");
        }else{
            JOptionPane.showMessageDialog(this,"数据库似乎有点问题...");
        }

    }


}
