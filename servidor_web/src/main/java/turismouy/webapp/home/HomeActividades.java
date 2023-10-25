package turismouy.webapp.home;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import turismouy.webapp.utils.LocalDateAdapter;

@WebServlet("/homeActividades")
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

    // @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gson gson = new Gson();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        String categoria    = request.getParameter("categoria");
        String departamento = request.getParameter("departamento");

        log.info("Departamento: " + departamento);

        log.info("[homeActividades Post]");

        IActividadController IAC = Fabrica.getInstance().getIActividadController();
        
        List<dataActividad> actividadesList = IAC.getAllActividades();

        // Creamos una lista que contendrá las actividades a eliminar
        List<dataActividad> actEliminar = new ArrayList<dataActividad>();
        
        // Filtramos departamento
        if (!departamento.equals("")) {
            for (dataActividad actividad : actividadesList) {
                if (!actividad.getDepartamento().getNombre().equals(departamento)) {
                    actEliminar.add(actividad);
                    // log.info("[homeActividades] Actividad: " + actividad.getNombre() + " se elimina por el filtro departamento");
                }
            }
        }

        // Filtramos por categoria
        if (!categoria.equals("")) {
            for(dataActividad actividad : actividadesList) {
                if (!actividad.getDtCategorias().contains(categoria)) {
                    actEliminar.add(actividad);
                    // log.info("[homeActividades] Actividad: " + actividad.getNombre() + " se elimina por el filtro categoria");
                }
            }
        }

        // Elimina los elementos de la lista original
        actividadesList.removeAll(actEliminar);

        request.setCharacterEncoding("UTF-8");
        String resultadoJson = gson.toJson(actividadesList);
        response.setContentType("application/json");
        response.getWriter().write(resultadoJson);
    }
}
