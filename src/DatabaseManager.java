import  org.mindrot.jbcrypt.BCrypt;
import javax.sql.*;
import java.sql.*;


public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bank_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "bank_user";
    private static String PASS = "123456";

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

}
