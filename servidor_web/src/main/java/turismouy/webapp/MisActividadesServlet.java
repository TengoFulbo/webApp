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
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.utilidades.log;
import turismouy.webapp.utils.LocalDateAdapter;

@WebServlet("/misActividades")
public class MisActividadesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("pageTitle", "Mis Actividades - TurismoUY");

        IActividadController IAC = Fabrica.getInstance().getIActividadController();
        IDepartamentoController IAD = Fabrica.getInstance().getIDepartamentoController();
        ICategoriaController ICC = Fabrica.getInstance().getICategoriaController();

        List<dataActividad> actividadesList = IAC.getAllActividades();

        List<dataDepartamento> departamentos = IAD.listarDepartamentos();

        List<dataCategoria> categorias = ICC.listarCategorias();

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
        
        dataUsuario usuario = (dataUsuario) session.getAttribute("dataUsuario");

        String nickname = usuario.getNickname();

        log.info("[misActividades] Accedemos");

        // Traemos la interfaz de actividades.
        IActividadController IAC = Fabrica.getInstance().getIActividadController();
        
        // Traemos las actividades.
        List<dataActividad> actividadesList = IAC.getAllActividades();

        // Creamos una lista que contendrá las actividades a eliminar
        List<dataActividad> actEliminar = new ArrayList<dataActividad>();

        log.info("Nickname a filtrar: " + nickname);

        // Filtramos para mostrar solo las pertenecientes al proveedor.
        for (dataActividad actividad : actividadesList) {
            log.info("Proveedor: " + nickname + " | Actividad: '" + actividad.getNombre() + "'");
            if (!actividad.getProveedor().getNickname().equals(nickname)) {
                actEliminar.add(actividad);
                log.info("La actividad: '" + actividad.getNombre() + "' se elimina.");
            }
        }

        // Filtramos departamento
        if (!departamento.equals("")) {
            for (dataActividad actividad : actividadesList) {
                if (!actividad.getDepartamento().getNombre().equals(departamento)) {
                    actEliminar.add(actividad);
                    // log.info("[misActividades] Actividad: " + actividad.getNombre() + " se elimina por el filtro departamento");
                }
            }
        }

        // Filtramos por categoria
        if (!categoria.equals("")) {
            for(dataActividad actividad : actividadesList) {
                if (!actividad.getDtCategorias().contains(categoria)) {
                    actEliminar.add(actividad);
                    // log.info("[misActividades] Actividad: " + actividad.getNombre() + " se elimina por el filtro categoria");
                }
            }
        }

        // Elimina los elementos de la lista original
        actividadesList.removeAll(actEliminar);

        log.warning("Cantidad de actividades que se pasan: " + actividadesList.isEmpty());
        request.setCharacterEncoding("UTF-8");
        String resultadoJson = gson.toJson(actividadesList);
        response.setContentType("application/json");
        response.getWriter().write(resultadoJson);
    }
}
