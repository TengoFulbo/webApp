package turismouy.webapp;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/error404")
public class NotFoundServlet extends HttpServlet {
    // ... (código del servlet)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, jakarta.servlet.ServletException {
    // Configurar la respuesta HTTP 404
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    request.getRequestDispatcher("/WEB-INF/errorNotFound.jsp").forward(request, response);
    }

}
