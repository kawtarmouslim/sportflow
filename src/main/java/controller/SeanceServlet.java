package controller;

import dao.SeanceDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Seance;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/seance")
public class SeanceServlet extends HttpServlet {
    SeanceDao seanceDao ;
    @Override
    public void init() throws ServletException {
        try {
            seanceDao = new SeanceDao();
            System.out.println("SeanceDao initialisé et tables créées !");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de l'initialisation de SeanceDao", e);
        }
        seanceDao.getAllMembres();
        seanceDao.getAllEntraineurs();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> membresList = seanceDao.getAllMembres();
        List<User> entraineursList = seanceDao.getAllEntraineurs();

        // Passer les données à la JSP
        req.setAttribute("membresList", membresList);
        req.setAttribute("entraineursList", entraineursList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("seance.jsp");
        dispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "create":
                addSeance(req, resp);
                break;
            case "edit":
                break;
            case "delete":
                break;
            default:
                break;
        }

    }

    private void addSeance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        int idEntraineur = Integer.parseInt(req.getParameter("entraineur_id"));
        int idMembre = Integer.parseInt(req.getParameter("member_id"));
        Seance seance = new Seance(date,idEntraineur,idMembre);
        seanceDao.addSeance(seance);
        resp.sendRedirect("seance.jsp");

    }
}