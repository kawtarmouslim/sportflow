package dao;

import model.Seance;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SeanceDao {
    Connection connection;
    private String url = "jdbc:mysql://localhost:3306/sportflow";
    private String username = "root";
    private String password = "";

    public SeanceDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String createSeanceTable = "CREATE TABLE IF NOT EXISTS seance (" +
                    "idSeance INT AUTO_INCREMENT PRIMARY KEY,"+
           " dateHeure DATETIME NOT NULL,"+
           "member_id INT," +
            "entraineur_id INT,"+
            "FOREIGN KEY (member_id) REFERENCES member(member_id),"+
                    "FOREIGN KEY (entraineur_id) REFERENCES entraineur(entraineur_id)"+
                    ")";


            statement.executeUpdate(createSeanceTable);
            System.out.println("Table 'condidat' created successfully (if it did not exist already).");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<User> getAllMembres() {
        List<User> membresList = new ArrayList<>();
        String sql = "SELECT user_id, nom FROM user WHERE role = 'member'";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User membre = new User();
                membre.setUser_id(resultSet.getInt("user_id"));
                membre.setNom(resultSet.getString("nom"));
                membresList.add(membre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membresList;
    }

    // Vous pouvez également créer une méthode similaire pour récupérer les entraîneurs.
    public List<User> getAllEntraineurs() {
        List<User> entraineursList = new ArrayList<>();
        String sql = "SELECT user_id, nom FROM user WHERE role = 'entraineur'";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User entraineur = new User();
                entraineur.setUser_id(resultSet.getInt("user_id"));
                entraineur.setNom(resultSet.getString("nom"));
                entraineursList.add(entraineur);
                System.out.println(entraineur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entraineursList;
    }

    public void addSeance(Seance seance) {
        String sql = "INSERT INTO seance (dateHeure, member_id, entraineur_id) VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
          preparedStatement.setString(1,seance.getDateTime());
            preparedStatement.setInt(3,seance.getIdMembre());
          preparedStatement.setInt(2,seance.getIdEntraineur());

          preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



}
