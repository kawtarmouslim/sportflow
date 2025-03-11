package dao;

import com.sun.jdi.connect.Connector;
import config.HibernateConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class UserDao {
    public void insertUser(User user)  {
        try(Session session = HibernateConfig.getSession().openSession()) {
           Transaction tx = session.beginTransaction();
           session.persist(user);
           tx.commit();
        }
    }
}
