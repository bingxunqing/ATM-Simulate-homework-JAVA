import javax.swing.*;
import java.awt.*;

public class BankGUI extends JFrame {
    private Account account;

    private JLabel moneyLabel;
    private JLabel monetLabelValue;
    private JTextField moneyField;
    private JButton subtractButton;
    private JButton addButton;

    public BankGUI() {
        account = new Account("奶农",1000f);
        setTitle("奶农银行kalakala");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        moneyLabel = new JLabel("你现在的余额：");
        monetLabelValue = new JLabel(String.valueOf(account.getMoney()));
        moneyField = new JTextField(10);
        addButton = new JButton("存钱啦");
        subtractButton = new JButton("取钱啦");

        setLayout(new GridLayout(3,1));

        JPanel monetPanel = new JPanel();
        monetPanel.add(moneyLabel);
        monetPanel.add(monetLabelValue);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("输入数字："));
        inputPanel.add(moneyField);

        add(inputPanel);
        add(monetPanel);
        add(buttonPanel);

        addButton.addActionListener(e ->{
            try{
                String moneyStr = moneyField.getText();
                double money = Double.parseDouble(moneyStr);
                account.addMoney(money);
                monetLabelValue.setText(String.valueOf(account.getMoney()));
                JOptionPane.showMessageDialog(this,"呀，你存钱啦？");
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this,"你输啥呢?");
            }
        });

        subtractButton.addActionListener(e ->{
            try{
                String moneyStr = moneyField.getText();
                double money = Double.parseDouble(moneyStr);
                account.subtractMoney(money);
                monetLabelValue.setText(String.valueOf(account.getMoney()));
                JOptionPane.showMessageDialog(this,"唉别取太多啦！");
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this,"你输啥呢？");
            }
        });

        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            new BankGUI();
        });
    }
}
