package turismouy.webapp.consultas;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turismouy.svcentral.middlewares.DataActividad;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.svcentral.utilidades.log;

@WebServlet("/consultaActividad")
public class ConsultaActividadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean debug = false;
        if (debug) log.info("Se accedio a Consulta de actividad");

        String nombreActividad = request.getParameter("nombreActividad");
        
        if (debug) log.info("Recibiendo el nombre: " + nombreActividad);

        // Llamamos al web service.
        Publicador API = new PublicadorService().getPublicadorPort();

        DataActividad actividad = API.actividadGetActividad(nombreActividad);


        if (debug) log.info("-------------------------------");
        if (debug) log.info("Actividad: " + actividad.getNombre());
        if (debug) log.info("Descripcion: " + actividad.getDesc());
        if (debug) log.info("Departamento: " + actividad.getDepartamento().getNombre());
        if (debug) log.info("FechaCrea: " + actividad.getFechaCrea());
        if (debug) log.info("-------------------------------");

        // actividad.getDtSalidas().get(1).getNombre();

        // Titulo de la web
        request.setAttribute("pageTitle", "Consulta de Actividad - TurismoUY");

        request.setAttribute("actividad", actividad);

        // Redireciona
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/consultas/consultaActividad.jsp");
        dispatcher.forward(request, response);
    }
}
