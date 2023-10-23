package turismouy.webapp.home;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.utilidades.log;

@WebServlet("/homeSalidas")
public class HomeSalidas extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", "Salidas - TurismoUY");

        ISalidaController ISC = Fabrica.getInstance().getISalidaController();

        List<dataSalida> salidas = ISC.getAllSalidas();

        request.setAttribute("salidas", salidas);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/homeSalidas.jsp");
        dispatcher.forward(request, response);
    }
}
