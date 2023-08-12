import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Percobaan {
    public static void main(String[] args) {
//        z:
//        for (int i = 1; i <= 50; i++) {
//            for (int j = 1; j <= 50; j++) {
//                System.out.println(i + " x " + j + " = " + i * j);
//                if (j == 10) {
//                    if (i == 10) break z;
//                    break;
//                }
//            }
//        }
        connect();
    }
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:P:\\Kuliah\\Database\\country.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}