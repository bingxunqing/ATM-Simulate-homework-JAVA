public class Account {
    private String name;
    private double money;

    public Account(String name, double money) {
        this.name = name;
        if (money > 0){
            this.money = money;
        }else {
            this.money = 0;
        }
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setAccount(Account account) {
        this.money = account.getMoney();
        this.name = account.getName();
    }

    public void addMoney(double money) {
       if (money > 0){
           this.money += money;
       }else{
           System.out.println("重新检查存款金额");
       }
    }

    public void subtractMoney(double money) {
        if(this.money < money){
            System.out.println("你有几个子你就取？");
        }else {
            this.money -= money;
        }
    }

}