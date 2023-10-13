package turismouy.webapp.auth;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cerrarSesion")
public class closeSessionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        session.removeAttribute("username");

        // Redireciona
        // RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
        
        // dispatcher.forward(request, response);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
