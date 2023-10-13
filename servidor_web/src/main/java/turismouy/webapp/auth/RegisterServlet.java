package turismouy.webapp.auth;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", "Registro - TurismoUY");

        // Muestra el formulario de inicio de sesión
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/register.jsp");
        dispatcher.forward(request, response);
    }
    // TODO: ⚠️ Falta implementar registro.
}
