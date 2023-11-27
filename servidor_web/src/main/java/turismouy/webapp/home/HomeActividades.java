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

//import turismouy.svcentral.interfaces.IActividadController;
//import turismouy.svcentral.interfaces.IDepartamentoController;
//import turismouy.svcentral.Fabrica;
//import turismouy.svcentral.interfaces.ICategoriaController;
//import turismouy.svcentral.datatypes.dataActividad;
//import turismouy.svcentral.datatypes.dataCategoria;
//import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.webapp.utils.log;
import turismouy.svcentral.middlewares.DataActividad;
import turismouy.svcentral.middlewares.DataCategoria;
import turismouy.svcentral.middlewares.DataDepartamento;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.webapp.utils.LocalDateAdapter;

@WebServlet("/homeActividades")
public class HomeActividades extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("pageTitle", "Actividades - TurismoUY");
        
        Publicador API = new PublicadorService().getPublicadorPort();

        //IActividadController IAC = Fabrica.getInstance().getIActividadController();
        //IDepartamentoController IAD = Fabrica.getInstance().getIDepartamentoController();
        //ICategoriaController ICC = Fabrica.getInstance().getICategoriaController();

        //List<DataActividad> actividadesList = IAC.getAllActividades();
        List<DataActividad> actividadesList = API.actividadGetAllActividades();

        //List<DataDepartamento> departamentos = IAD.listarDepartamentos();
        List<DataDepartamento> departamentos = API.departamentoListarDepartamentos();

        //List<DataCategoria> categorias = ICC.listarCategorias();
        List<DataCategoria> categorias = API.categoriaListarCategorias();

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

        // log.info("Departamento: " + departamento);

        log.info("[homeActividades Post]");

        //IActividadController IAC = Fabrica.getInstance().getIActividadController();
        Publicador API = new PublicadorService().getPublicadorPort();
        
        // List<dataActividad> actividadesList = IAC.getAllActividades();
        List<DataActividad> actividadesList = API.actividadGetAllActividades();

        if (actividadesList == null) {
            System.out.println("[homeActividades] No hay actividades");
            // response.getWriter().write("");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Creamos una lista que contendrá las actividades a eliminar
        // List<dataActividad> actEliminar = new ArrayList<dataActividad>();
        List<DataActividad> actEliminar = new ArrayList<DataActividad>();
        
        // Filtramos departamento
        if (!departamento.equals("")) {
            for (DataActividad actividad : actividadesList) {
                if (!actividad.getDepartamento().getNombre().equals(departamento)) {
                    actEliminar.add(actividad);
                    // log.info("[homeActividades] Actividad: " + actividad.getNombre() + " se elimina por el filtro departamento");
                }
            }
        }

        // Filtramos por categoria
        if (!categoria.equals("")) {
            for(DataActividad actividad : actividadesList) {
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
