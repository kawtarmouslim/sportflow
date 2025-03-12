package dao;

import model.Entraineur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EntraineurDao {
    Connection connection;
    private String url = "jdbc:mysql://localhost:3306/sportflow";
    private String username = "root";
    private String password = "";

    public EntraineurDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String entraineur = "CREATE TABLE IF NOT EXISTS entraineur (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT UNIQUE, " +
                    "specialite VARCHAR(50), " +
                    "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE" +
                    ");";
            statement.executeUpdate(entraineur);
            System.out.println("Table 'condidat' created successfully (if it did not exist already).");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void addEntraineur(Entraineur entraineur) {
       String query = "INSERT INTO entraineur (user_id, specialite) VALUES (?, ?)";
       try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
           preparedStatement.setInt(1, entraineur.getId());
           preparedStatement.setString(2, entraineur.getSpecialite());
           preparedStatement.executeUpdate();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

    public static void main(String[] args) {
        EntraineurDao entraineurDao = new EntraineurDao();
        System.out.println("cree table Entraineur");
    }
}

