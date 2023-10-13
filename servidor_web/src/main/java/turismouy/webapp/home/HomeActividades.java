package turismouy.webapp.home;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/homeActividades")
public class HomeActividades extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("pageTitle", "Actividades - TurismoUY");


        // Redireciona
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/homeActividades.jsp");
        dispatcher.forward(request, response);
    }
}
