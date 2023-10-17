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

import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.utilidades.log;

@WebServlet("/homeActividad")
public class HomeActividades extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("pageTitle", "Actividades - TurismoUY");

        IActividadController IAC = Fabrica.getInstance().getIActividadController();

        List<dataActividad> actividadesList = IAC.getAllActividades();

        request.setAttribute("actividades", actividadesList);
        log.info("Se accedio a actividades");
        // Redireciona
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/homeActividades.jsp");
        dispatcher.forward(request, response);
    }
}
