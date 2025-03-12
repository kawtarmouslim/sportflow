package dao;

import model.Membre;

import java.sql.*;

public class MembreDao  {
    Connection connection;
    private String url = "jdbc:mysql://localhost:3306/sportflow";
    private String username = "root";
    private String password = "";

    public MembreDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String membre = "CREATE TABLE IF NOT EXISTS membre (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT UNIQUE, " +
                    "sportPratique VARCHAR(50), " +
                    "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE" +
                    ");";

            statement.executeUpdate(membre);
            System.out.println("Table 'membre' created successfully (if it did not exist already).");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMembre(Membre membre) {
       String query = "INSERT INTO membre (user_id, sprotPratique) VALUES (?, ?)";
       try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
           preparedStatement.setInt(1, membre.getIdMembre());
           preparedStatement.setString(2, membre.getSportPratique());
           preparedStatement.executeUpdate();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

   }
    public static void main(String[] args) {
        MembreDao membreDao = new MembreDao();
        System.out.println("cree table Membre");
    }
}
