package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

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
            String seance = "CREATE TABLE IF NOT EXISTS seance (" +
                    "idSeance INT AUTO_INCREMENT PRIMARY KEY, " +
                    "dateHeure DATETIME NOT NULL, " +
                    "idMembre int NOT NULL, " +
                    "idEntraineur int ," +
                    "FOREIGN KEY (idMembre) REFERENCES Membre(idMembre), " +
                    "FOREIGN KEY (idEntraineur) REFERENCES Entraineur(idEntraineur)" +

                    ");";

            statement.executeUpdate(seance);
            System.out.println("Table 'condidat' created successfully (if it did not exist already).");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SeanceDao seanceDao = new SeanceDao();
        System.out.println("cree table Seance");
    }


}
