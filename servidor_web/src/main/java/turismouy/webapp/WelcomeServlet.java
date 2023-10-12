package turismouy.webapp;

import java.io.IOException;
// import javax.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    // ... (mismo código que antes)
    public void init() throws ServletException {
        System.out.println("El Servlet HolaMundoServlet se está inicializando.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<html><body><h1>Hola Mundo desde un Servlet</h1></body></html>");
    }
}
