package dao;


import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String url = "jdbc:mysql://localhost:3306/sportflow";
    private String username = "root";
    private String password = "";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trouvé: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    // Méthode pour créer la table 'user' si elle n'existe pas
    public void createUserTable() {
        String createUserTableSQL = "CREATE TABLE IF NOT EXISTS user (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nom VARCHAR(100) NOT NULL, " +
                "prenom VARCHAR(100) NOT NULL, " +
                "dataNaissance Date NOT NULL, " +
                "email VARCHAR(255), " +
                "`password` VARCHAR(255), " +
                "tel VARCHAR(20), " +
                "roles  VARCHAR(20)" +
                ");";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createUserTableSQL);
            System.out.println("Table 'user' créée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de la table : " + e.getMessage());
            e.printStackTrace();
        }

    }
    public void createUser(User user) {
        String sql = "INSERT INTO user (nom, prenom, dataNaissance, email, password, tel, roles) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getDateNaissance());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getTel());
            preparedStatement.setString(7, user.getRole()); // Ajouter ce paramètre pour le rôle

            // Utilisez executeUpdate() pour l'insertion
            preparedStatement.executeUpdate();

            // Récupérer l'ID généré par la base de données
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));  // Attribuer l'ID généré à l'utilisateur
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getGeneratedUserId() {
        String sql = "SELECT LAST_INSERT_ID()";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1); // Récupère l'ID généré
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération de l'ID généré.", e);
        }
        return -1; // Retourne -1 en cas d'erreur
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setDateNaissance(resultSet.getString("dataNaissance"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setTel(resultSet.getString("tel"));
                users.add(user);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public User getUser(String email) {
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("roles")
                );
            }

                return user;

            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }



    }





























//    public void insertUser(User user)  {
//        try(Session session = HibernateConfig.getSession().openSession()) {
//           Transaction tx = session.beginTransaction();
//           session.persist(user);
//           tx.commit();
//        }
//    }
}
