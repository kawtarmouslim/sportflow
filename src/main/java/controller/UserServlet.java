package controller;


import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserDao userDAO;

    @Override
    public void init() {
        try {
            System.out.println("Début de l'initialisation de UserServlet...");
            userDAO = new UserDao();
            System.out.println("UserDao initialisé.");
            userDAO.createUserTable();
            System.out.println("Table utilisateur créée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "adduser":
                    addUser(request, response);
                    break;
                    case "listuser":
                        listuser(request, response);
                            break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("adduser.jsp");
        dispatcher.forward(request, response);
    }
    private void listuser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<User> users = userDAO.getUsers();
        request.setAttribute("users", users); // Vérification du nom correct
        RequestDispatcher dispatcher = request.getRequestDispatcher("listuser.jsp");
        dispatcher.forward(request, response);
    }
//adduser
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String last_name = request.getParameter("nom");
        String first_name = request.getParameter("prenom");
        String birth_date = request.getParameter("date");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String Telephone = request.getParameter("tel");
        String role = request.getParameter("role");
        String sport = request.getParameter("sportPratique");
        String speciality = request.getParameter("specialite");
        User newUser = new User(last_name,first_name,birth_date,email,password,Telephone,role,sport,speciality);
        userDAO.addUser(newUser);

        response.sendRedirect(request.getContextPath() + "/user?action=listuser");

    }

}
