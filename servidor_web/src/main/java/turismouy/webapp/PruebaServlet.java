package turismouy.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/prueba")
public class PruebaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Procesa los datos o realiza cálculos
        int resultado = 42;
        System.out.println("Prueba");

        // Configura un atributo en el request para pasar información a la página JSP
        request.setAttribute("resultado", resultado);

        // Redirecciona a la página JSP
        request.getRequestDispatcher("/prueba.jsp").forward(request, response);
    }
}