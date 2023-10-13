package turismouy.webapp.home;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        // session.setAttribute("username", username);
        String usuario = (String) session.getAttribute("username");

        request.setAttribute("pageTitle", "Home - TurismoUY");

        request.setAttribute("usuario", usuario);
        request.setAttribute("correo", "prueba@gmail.com");
        request.setAttribute("salidas", "5");
        request.setAttribute("compras", "12");
        request.setAttribute("actividades", "100");


        // Redireciona
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }
}
