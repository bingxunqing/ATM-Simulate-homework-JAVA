import  org.mindrot.jbcrypt.BCrypt;
import javax.sql.*;
import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://48.210.84.122:3306/bank_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "bank_app_user";
    private static String PASS = "Bank_app_p@ssw0rd!2025";

    private Connection connect(){
        try{
            return DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (SQLException e){
            System.err.println("糟糕，数据库呢？");
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean userExists(String username){
        String sql = "SELECT username FROM users WHERE username = ?";
        try(Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            if (conn == null){
                return true;
            }
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            return  rs.next();
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println(e.getMessage()+"2");
            return true;
        }
    }

    public boolean createAccount(String username, String password){
        if(userExists(username)){
            System.out.println("创建失败，你的英明想法"+username+"已经被人用了噢");
            return false;
        }

        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
        String sql = "INSERT INTO users(username,password_hash,balance) VALUES (?,?,?)";

        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            if(conn == null){
                return false;
            }

            pstmt.setString(1,username);
            pstmt.setString(2,hashedPassword);
            pstmt.setDouble(3,0.0);

            int affectedRows = pstmt.executeUpdate();

            return (affectedRows > 0);
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println(e.getMessage()+"3");
            return false;
        }
    }

    public boolean login(String username, String password){
        String sql = "SELECT password_hash FROM users WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null){
                return false;
            }

            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                String storedHash = rs.getString("password_hash");
                return BCrypt.checkpw(password,storedHash);
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println(e.getMessage()+"4");
            return false;
        }
    }

    public double getBalance(String username){
        String sql = "SELECT balance FROM users WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null){
                return -1;
            }
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return rs.getDouble("balance");
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println(e.getMessage()+"5");
        }
        return -1;
    }

    public boolean updateBalance(String username, double newBalance){
        if(newBalance < 0){
            return false;
        }
        String sql = "UPDATE users SET balance = ? WHERE username = ?";
        try(Connection conn = this.connect();PreparedStatement pstmt = conn.prepareStatement(sql) ){
            if(conn == null){
                return false;
            }
            pstmt.setDouble(1,newBalance);
            pstmt.setString(2,username);

            int affectRows = pstmt.executeUpdate();
            return (affectRows > 0);
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println(e.getMessage()+"6");
            return false;
        }
    }

    public boolean updatePassword(String username,String newPassword){
        String sql = "UPDATE users SET password_hash = ? WHERE username = ?";
        try(Connection conn = this.connect();PreparedStatement pstmt = conn.prepareStatement(sql)){
            if(conn == null){
                return false;
            }
            String newPasswordHash = BCrypt.hashpw(newPassword,BCrypt.gensalt());
            pstmt.setString(1,newPasswordHash);
            pstmt.setString(2,username);
            int affectRows = pstmt.executeUpdate();
            return (affectRows > 0);
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println(e.getMessage()+"7");
            return false;
        }
    }

}
