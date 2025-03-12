package controller;

import dao.EntraineurDao;
import dao.MembreDao;
import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Entraineur;
import model.Membre;
import model.User;

import java.io.IOException;

@WebServlet("/create")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des données depuis le formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String dateNaissance = request.getParameter("date");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String role = request.getParameter("role");

        // Créer un objet User avec les informations de base
        User user = new User();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setDateNaissance(dateNaissance);
        user.setEmail(email);
        user.setPassword(password);
        user.setTel(tel);
        user.setRole(role);
        // Créer une instance du UserDAO pour enregistrer l'utilisateur dans la base de données
        UserDao userDAO = new UserDao();
        userDAO.createUser(user);  // Insérer l'utilisateur dans la base de données
        // Récupérer l'ID généré après l'insertion
        int userId = userDAO.getGeneratedUserId();
        // Si l'utilisateur est enregistré, on crée le membre ou l'entraîneur
        if (userId > 0) {
            if ("trainer".equals(role)) {
                String specialite = request.getParameter("specialite");
                Entraineur entraineur = new Entraineur(String.valueOf(userId), specialite);
                EntraineurDao entraineurDao = new EntraineurDao();
                entraineurDao.addEntraineur(entraineur);  // Sauvegarde l'entraîneur
            } else if ("member".equals(role)) {
                String sportPratique = request.getParameter("sportPratique");
                Membre member = new Membre(userId, sportPratique);
                MembreDao memberDAO = new MembreDao();
                memberDAO.addMembre(member);  // Sauvegarde le membre
            }
            // Redirection après l'ajout de l'utilisateur
            response.sendRedirect("index.jsp");
        } else {
            // Si l'insertion échoue, redirection vers une page d'erreur
            request.setAttribute("errorMessage", "Erreur lors de l'ajout de l'utilisateur.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

}
