package turismouy.webapp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.middlewares.DataActividad;
import turismouy.svcentral.middlewares.DataCategoria;
import turismouy.svcentral.middlewares.DataDepartamento;
import turismouy.svcentral.middlewares.DataUsuario;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.svcentral.Fabrica;
import turismouy.webapp.utils.log;
//import turismouy.svcentral.datatypes.dataActividad;
//import turismouy.svcentral.datatypes.dataCategoria;
//import turismouy.svcentral.datatypes.dataDepartamento;
//import turismouy.svcentral.datatypes.dataUsuario;
//import turismouy.svcentral.utilidades.log;
import turismouy.webapp.utils.LocalDateAdapter;

@WebServlet("/misActividades")
public class MisActividadesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Mis Actividades - TurismoUY");

        Publicador API = new PublicadorService().getPublicadorPort();

        List<DataActividad> actividadesList = API.actividadGetAllActividades();

        List<DataDepartamento> departamentos = API.departamentoListarDepartamentos();

        List<DataCategoria> categorias = API.categoriaListarCategorias();

        request.setAttribute("actividades", actividadesList);
        request.setAttribute("departamentos", departamentos);
        request.setAttribute("categorias", categorias);

        log.info("Se accedio a mis actividades");
        // Redireciona
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/misActividades.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        HttpSession session = request.getSession();
        String categoria    = request.getParameter("categoria");
        String departamento = request.getParameter("departamento");
        
        DataUsuario usuario = (DataUsuario) session.getAttribute("dataUsuario");

        String nickname = usuario.getNickname();

        log.info("[misActividades] Accedemos");

        Publicador API = new PublicadorService().getPublicadorPort();
        
        // Traemos las actividades.
        List<DataActividad> actividadesList = API.actividadGetAllActividades();

        if (actividadesList == null) {
            System.out.println("[misActividades] No hay actividades");
            // response.getWriter().write("");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Creamos una lista que contendrá las actividades a eliminar
        List<DataActividad> actEliminar = new ArrayList<DataActividad>();

        // log.info("Nickname a filtrar: " + nickname);

        // Filtramos para mostrar solo las pertenecientes al proveedor.
        for (DataActividad actividad : actividadesList) {
            log.info("Proveedor: " + nickname + " | Actividad: '" + actividad.getNombre() + "'");
            if (!actividad.getProveedor().getNickname().equals(nickname)) {
                actEliminar.add(actividad);
                // log.info("La actividad: '" + actividad.getNombre() + "' se elimina.");
            }
        }

        // Filtramos departamento
        if (!departamento.equals("")) {
            for (DataActividad actividad : actividadesList) {
                if (!actividad.getDepartamento().getNombre().equals(departamento)) {
                    actEliminar.add(actividad);
                    // log.info("[misActividades] Actividad: " + actividad.getNombre() + " se elimina por el filtro departamento");
                }
            }
        }

        // Filtramos por categoria
        if (!categoria.equals("")) {
            for(DataActividad actividad : actividadesList) {
                if (!actividad.getDtCategorias().contains(categoria)) {
                    actEliminar.add(actividad);
                    // log.info("[misActividades] Actividad: " + actividad.getNombre() + " se elimina por el filtro categoria");
                }
            }
        }

        // Elimina los elementos de la lista original
        actividadesList.removeAll(actEliminar);

        // log.warning("Cantidad de actividades que se pasan: " + actividadesList.isEmpty());
        request.setCharacterEncoding("UTF-8");
        String resultadoJson = gson.toJson(actividadesList);
        response.setContentType("application/json");
        response.getWriter().write(resultadoJson);
    }
}
