package turismouy.webapp;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/resultado")
public class ResultadoServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establece datos que deseas enviar a la página JSP
        String mensaje = "¡Hola desde el servlet!";
        request.setAttribute("mensaje", mensaje);

        // Redirige a la página JSP
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
