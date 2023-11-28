package turismouy.webapp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.servlet.http.HttpSession;

//import turismouy.svcentral.interfaces.IActividadController;
//import turismouy.svcentral.interfaces.ICategoriaController;
//import turismouy.svcentral.interfaces.IDepartamentoController;
//import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.middlewares.DataActividad;
import turismouy.svcentral.middlewares.DataCategoria;
import turismouy.svcentral.middlewares.DataDepartamento;
import turismouy.svcentral.middlewares.DataSalida;
import turismouy.svcentral.middlewares.DataUsuario;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.webapp.utils.log;
//import turismouy.svcentral.datatypes.dataCategoria;
//import turismouy.svcentral.datatypes.dataActividad;
//import turismouy.svcentral.datatypes.dataUsuario;
//import turismouy.svcentral.entidades.actividad;
//import turismouy.svcentral.entidades.departamento;
//import turismouy.svcentral.entidades.departamento;
//import turismouy.svcentral.entidades.categoria;
//import turismouy.svcentral.datatypes.dataSalida;
//import turismouy.svcentral.datatypes.dataDepartamento;
//import turismouy.svcentral.utilidades.log;
import turismouy.webapp.utils.LocalDateAdapter;

@WebServlet("/misSalidas")
public class MisSalidasSerlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", "Mis Salidas - TurismoUY");

        Publicador API = new PublicadorService().getPublicadorPort();

        List<DataActividad> actividadesList = API.actividadGetAllActividades();
        List<DataDepartamento> departamentos = API.departamentoListarDepartamentos();
        List<DataCategoria> categorias = API.categoriaListarCategorias();
        List<DataSalida> salidas = API.salidaGetAllSalidas();


        request.setAttribute("actividades", actividadesList);
        request.setAttribute("departamentos", departamentos);
        request.setAttribute("categorias", categorias);
        request.setAttribute("salidas", salidas);


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/misSalidas.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// Gson gson = new Gson();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        Publicador API = new PublicadorService().getPublicadorPort();

        String categoria    = request.getParameter("categoria");
        String departamento = request.getParameter("departamento");
        String actividad    = request.getParameter("actividad");

        DataUsuario usuario = (DataUsuario) session.getAttribute("dataUsuario");

        String nickname = usuario.getNickname();

        List<DataSalida> userList = usuario.getSalidas();

        List<DataSalida> filterList = null;

        List<DataSalida> respList = new ArrayList<DataSalida>();

        if (!actividad.equals("")){
            filterList = filtroA(actividad);
            log.info("Ordeno por actividad");
        }else{
            filterList = filtroDC(departamento, categoria);
            log.info("Ordeno por general");
        }

        if(userList != null && filterList != null) {
        	if(!userList.isEmpty() && !filterList.isEmpty()){
                for (DataSalida sal1 : filterList){
                	log.info("Salida1 -> " + sal1.getNombre());
                    for (DataSalida sal2 : userList){
                    	log.info("Salida2 -> " + sal2.getNombre());
                        if(sal1.getNombre().equals(sal2.getNombre())){
                            respList.add(sal1);
                        }
                        System.out.println("=======================================");
                    }
                }
            }
        }

        if(userList == null || userList.isEmpty()) {
            log.warning("====================================================");
            log.warning("...           -> Lista user no contiene nada");
            log.warning("====================================================");
        }
        if(filterList == null || filterList.isEmpty()) {
            log.warning("====================================================");
            log.warning("...           -> Lista filtros no contiene nada");
            log.warning("====================================================");
        }

        if (respList.isEmpty()) {
            log.warning("La lista de respuesta es vacia⛔");
        }else{
            log.warning("La lista de respuesta NO es vacia✅");
            for (DataSalida salida : respList){
                log.info(salida.getNombre());
            }
        }

        request.setCharacterEncoding("UTF-8");
        String resultadoJson = gson.toJson(respList);
        response.setContentType("application/json");
        response.getWriter().write(resultadoJson);
    }

    List<DataSalida> filtroA(String actividadName){
        Publicador API = new PublicadorService().getPublicadorPort();

        List<DataActividad> actividades = API.actividadGetAllActividades();
        DataActividad actividad = null;

        // log.info(actividadName + " tamaño de actividades -> " + actividades.size());
        if (actividades != null){
            if (!actividades.isEmpty()){
                for(DataActividad act : actividades){
                    // log.info("  -> " + act.getNombre());
                    if (act.getNombre().equals(actividadName)){
                        actividad = act;
                        break;
                    }
                }
            }
        }
        
        if (actividad == null){
            return null;
        }else{
            List<DataSalida> result = actividad.getDtSalidas();
            return result;
        }
    }

    List<DataSalida> filtroDC(String departamento, String categoria){

        Publicador API = new PublicadorService().getPublicadorPort();

        List<DataDepartamento> departamentos = API.departamentoListarDepartamentos();
        List<DataActividad> actividades = null;
        List<DataCategoria> categorias = API.categoriaListarCategorias();

        if (departamento.equals("") && categoria.equals("")){
            actividades = API.actividadGetAllActividades();
        }

        if (!departamento.equals("")){
            for(DataDepartamento dpto : departamentos){
                if(dpto.getNombre().equals(departamento)){
                    actividades = dpto.getActividades();
                    if(!categoria.equals("")){
                        List<DataActividad> foo = new ArrayList<DataActividad>();;
                        for(DataActividad act : actividades){
                            foo.add(act);
                        }
                        // log.warning(actividades.size() + "");
                        for(DataActividad act : foo){
                            if(!act.getDtCategorias().contains(categoria)){
                                actividades.remove(act);
                            }
                        }
                    }
                }
            }
        }else{
            if(!categoria.equals("")){
                actividades = API.actividadGetAllActividades();
                List<DataActividad> foo = new ArrayList<DataActividad>();;
                for(DataActividad act : actividades){
                    foo.add(act);
                }

                // log.warning(actividades.size() + "");
                for(DataActividad act : foo){
                    if(!act.getDtCategorias().contains(categoria)){
                        actividades.remove(act);
                    }
                }
            } 
        }

        if(actividades.isEmpty()){
            return null;
        }else{
            List<DataSalida> salidas = new ArrayList<DataSalida>();
            List<DataSalida> tmp = new ArrayList<DataSalida>();
            for(DataActividad act : actividades){
                tmp = act.getDtSalidas();
                for(DataSalida salida : tmp){
                    if(!salidas.contains(salida)){
                        salidas.add(salida);
                    }
                }
            }

            return salidas;
        }
    }
}

