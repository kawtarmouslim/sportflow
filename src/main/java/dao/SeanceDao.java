package dao;

import model.Seance;

import java.sql.*;


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
