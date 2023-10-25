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
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.utilidades.log;

@WebServlet("/homeActividad")
public class HomeActividades extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("pageTitle", "Actividades - TurismoUY");

        IActividadController IAC = Fabrica.getInstance().getIActividadController();
        IDepartamentoController IAD = Fabrica.getInstance().getIDepartamentoController();
        ICategoriaController ICC = Fabrica.getInstance().getICategoriaController();

        List<dataActividad> actividadesList = IAC.getAllActividades();

        List<dataDepartamento> departamentos = IAD.listarDepartamentos();

        List<dataCategoria> categorias = ICC.listarCategorias();

        request.setAttribute("actividades", actividadesList);
        request.setAttribute("departamentos", departamentos);
        request.setAttribute("categorias", categorias);

        log.info("Se accedio a actividades");
        // Redireciona
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/homeActividades.jsp");
        dispatcher.forward(request, response);
    }
}
