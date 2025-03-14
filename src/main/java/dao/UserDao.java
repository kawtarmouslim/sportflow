package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/sportflow";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public void createUserTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String sqlQuery1 = "CREATE TABLE IF NOT EXISTS user (" +
                    "user_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nom VARCHAR(100) NOT NULL, " +
                    "prenom VARCHAR(100) NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "password VARCHAR(100) NOT NULL, "+
                      "tel VARCHAR(100) NOT NULL, " +
                    "role VARCHAR(15) NOT NULL" +
                    ")";

            String sqlQuery2 = "CREATE TABLE IF NOT EXISTS member (" +
                    "member_id INT PRIMARY KEY, " +
                    "sportPratique VARCHAR(100) NOT NULL, " +
                    "CONSTRAINT fk_member_user FOREIGN KEY (member_id) REFERENCES user(user_id) ON DELETE CASCADE" +
                    ")";

            String sqlQuery3 = "CREATE TABLE IF NOT EXISTS entraineur (" +
                    "entraineur_id INT PRIMARY KEY, " +
                    "specialite VARCHAR(100) NOT NULL, " +
                    "CONSTRAINT fk_entraineur_user FOREIGN KEY (entraineur_id) REFERENCES user(user_id) ON DELETE CASCADE" +
                    ")";

            statement.executeUpdate(sqlQuery1);
            statement.executeUpdate(sqlQuery2);
            statement.executeUpdate(sqlQuery3);
            System.out.println("Tables 'user', 'member', and 'entraineur' created successfully!");

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public void addUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false); // Start transaction

            String insertUserQuery = "INSERT INTO user (nom, prenom, date, email, password,tel,role) VALUES (?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement userStatement = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
                userStatement.setString(1, user.getNom());
                userStatement.setString(2, user.getPrenom());
                userStatement.setDate(3, java.sql.Date.valueOf(user.getDateNaissance()));
                userStatement.setString(4, user.getEmail());
                userStatement.setString(5, user.getPassword());
                userStatement.setString(6, user.getTel());
                userStatement.setString(7, user.getRole());
                userStatement.executeUpdate();

                ResultSet generatedKeys = userStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);

                    if ("member".equalsIgnoreCase(user.getRole())) {
                        String insertMemberQuery = "INSERT INTO member (member_id, sportPratique) VALUES (?, ?)";
                        try (PreparedStatement memberStatement = connection.prepareStatement(insertMemberQuery)) {
                            memberStatement.setInt(1, userId);
                            memberStatement.setString(2, user.getSportPratique());
                            memberStatement.executeUpdate();
                        }
                    } else if ("entraineur".equalsIgnoreCase(user.getRole())) {
                        String insertEntraineurQuery = "INSERT INTO entraineur (entraineur_id, specialite) VALUES (?, ?)";
                        try (PreparedStatement entraineurStatement = connection.prepareStatement(insertEntraineurQuery)) {
                            entraineurStatement.setInt(1, userId);
                            entraineurStatement.setString(2, user.getSpecialite());
                            entraineurStatement.executeUpdate();
                        }
                    } else {
                        throw new SQLException("Invalid role: " + user.getRole());
                    }
                } else {
                    throw new SQLException("Failed to retrieve user_id.");
                }
            }

            connection.commit();
            System.out.println("User added successfully!");

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
            System.err.println("SQL Error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Créer un objet User et remplir ses champs
                User user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setDateNaissance(resultSet.getString("date")); // Assurez-vous que le format est correct
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setTel(resultSet.getString("tel"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des utilisateurs", e);
        }
        return users;
    }
    public void deleteUser(int userId) {
        try (Connection connection = getConnection()) {
            // 1. Supprimer les séances associées
            String deleteSeances = "DELETE FROM seance WHERE member_id = ?";
            try (PreparedStatement stmt1 = connection.prepareStatement(deleteSeances)) {
                stmt1.setInt(1, userId);
                stmt1.executeUpdate();
            }
            // 2. Supprimer le membre (vérifie si tu dois supprimer dans user ou member, ça dépend de ton modèle)
            String deleteUser = "DELETE FROM user WHERE user_id = ?";
            try (PreparedStatement stmt2 = connection.prepareStatement(deleteUser)) {
                stmt2.setInt(1, userId);
                stmt2.executeUpdate();
                System.out.println("Utilisateur supprimé avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public User getUser(int userId) {
        User user = null;
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setNom(resultSet.getString("nom"));

                user.setPrenom(resultSet.getString("prenom"));
                user.setDateNaissance(resultSet.getString("date"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setTel(resultSet.getString("tel"));
                user.setRole(resultSet.getString("role"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE etudiant SET nom=?, prenom=?,  dateNaissance=?,email=?,password=?, tel=?,role=?,sportPratique=?,specialite=?,WHERE user_id=?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getPrenom());
            stmt.setString(3, user.getDateNaissance());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setString(5, user.getTel());
            stmt.setString(5, user.getRole());
            stmt.setString(6, user.getSportPratique());
            stmt.setString(7, user.getSpecialite());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
