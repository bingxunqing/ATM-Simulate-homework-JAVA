public class TestDatabase {

    public static void main(String[] args) {
        System.out.println("开始测试");

        DatabaseManager dbManager = new DatabaseManager();

        String testUsername = "root";
        String testPassword = "123";
        String wrongUsername = "wrongUsername";
        String wrongPassword = "wrongPassword";

        System.out.println("[测试1]测试创建账户："+testUsername);
        boolean createSuccess = dbManager.createAccount(testUsername, testPassword);
        if (createSuccess) {
            System.out.println("成功创建账户");
        }else{
            System.out.println("创建失败");
        }

        System.out.println("\n[测试2] 尝试再次创建同名账户: " + testUsername);
        boolean createAgainSuccess = dbManager.createAccount(testUsername, testPassword);
        if (!createAgainSuccess) {
            System.out.println("结果: 成功！系统按预期拒绝了重复创建。");
        } else {
            System.out.println("结果: 失败！系统不应该允许创建同名账户。");
        }

        // --- 测试场景 3: 使用正确的密码登录 ---
        System.out.println("\n[测试3] 尝试使用正确密码登录: " + testUsername);
        boolean loginSuccess = dbManager.login(testUsername, testPassword);
        if (loginSuccess) {
            System.out.println("结果: 成功！密码验证通过。");
        } else {
            System.out.println("结果: 失败！检查 login 方法的逻辑或数据库连接。");
        }

        // --- 测试场景 4: 使用错误的密码登录 ---
        System.out.println("\n[测试4] 尝试使用错误密码登录: " + testUsername);
        boolean loginFail = dbManager.login(testUsername, wrongPassword);
        if (!loginFail) {
            System.out.println("结果: 成功！系统按预期拒绝了错误密码。");
        } else {
            System.out.println("结果: 失败！系统不应该让错误的密码通过验证。");
        }

        System.out.println("\n--- 数据库连接测试结束 ---");
    }

}
