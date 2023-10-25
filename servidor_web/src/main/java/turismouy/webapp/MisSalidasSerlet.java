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

import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.departamento;
import turismouy.svcentral.entidades.departamento;
import turismouy.svcentral.entidades.categoria;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.utilidades.log;
import turismouy.webapp.utils.LocalDateAdapter;

@WebServlet("/misSalidas")
public class MisSalidasSerlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", "Mis Salidas - TurismoUY");

        IActividadController IAC = Fabrica.getInstance().getIActividadController();
        IDepartamentoController IAD = Fabrica.getInstance().getIDepartamentoController();
        ICategoriaController ICC = Fabrica.getInstance().getICategoriaController();
        ISalidaController ISC = Fabrica.getInstance().getISalidaController();

        List<dataActividad> actividadesList = IAC.getAllActividades();
        List<dataDepartamento> departamentos = IAD.listarDepartamentos();
        List<dataCategoria> categorias = ICC.listarCategorias();
        List<dataSalida> salidas = ISC.getAllSalidas();


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

        IActividadController IAC = Fabrica.getInstance().getIActividadController();
        ISalidaController ISC = Fabrica.getInstance().getISalidaController();

        String categoria    = request.getParameter("categoria");
        String departamento = request.getParameter("departamento");
        String actividad    = request.getParameter("actividad");

        dataUsuario usuario = (dataUsuario) session.getAttribute("dataUsuario");

        String nickname = usuario.getNickname();

        List<dataSalida> userList = usuario.getSalidas();

        List<dataSalida> filterList = null;

        List<dataSalida> respList = new ArrayList<dataSalida>();

        if (!actividad.equals("")){
            filterList = filtroA(actividad);
            log.info("Ordeno por actividad");
        }else{
            filterList = filtroDC(departamento, categoria);
            log.info("Ordeno por general");
        }

        if(userList != null && filterList != null) {
        	if(!userList.isEmpty() && !filterList.isEmpty()){
                for (dataSalida sal1 : filterList){
                	log.info("Salida1 -> " + sal1.getNombre());
                    for (dataSalida sal2 : userList){
                    	log.info("Salida2 -> " + sal2.getNombre());
                        if(sal1.getNombre().equals(sal2.getNombre())){
                            respList.add(sal1);
                        }
                        System.out.println("=======================================");
                    }
                }
            }
        }

        if (respList.isEmpty()) {
            log.warning("La lista de respuesta NO es vacia✅");
            for (dataSalida salida : respList){
                log.info(salida.getNombre());
            }
        }else{
            log.warning("La lista de respuesta es vacia⛔");
        }

        request.setCharacterEncoding("UTF-8");
        String resultadoJson = gson.toJson(respList);
        response.setContentType("application/json");
        response.getWriter().write(resultadoJson);
    }

    List<dataSalida> filtroA(String actividadName){
        IActividadController IAC = Fabrica.getInstance().getIActividadController();

        List<dataActividad> actividades = IAC.getAllActividades();
        dataActividad actividad = null;

        // log.info(actividadName + " tamaño de actividades -> " + actividades.size());
        if (actividades != null){
            if (!actividades.isEmpty()){
                for(dataActividad act : actividades){
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
            List<dataSalida> result = actividad.getDtSalidas();
            return result;
        }
    }

    List<dataSalida> filtroDC(String departamento, String categoria){

        IActividadController IAC = Fabrica.getInstance().getIActividadController();
        ISalidaController ISC = Fabrica.getInstance().getISalidaController();
        IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
        ICategoriaController ICC = Fabrica.getInstance().getICategoriaController();

        List<dataDepartamento> departamentos = IDC.listarDepartamentos();
        List<dataActividad> actividades = null;
        List<dataCategoria> categorias = ICC.listarCategorias();

        if (departamento.equals("") && categoria.equals("")){
            actividades = IAC.getAllActividades();
        }

        if (!departamento.equals("")){
            for(dataDepartamento dpto : departamentos){
                if(dpto.getNombre().equals(departamento)){
                    actividades = dpto.getActividades();
                    if(!categoria.equals("")){
                        List<dataActividad> foo = new ArrayList<dataActividad>();;
                        for(dataActividad act : actividades){
                            foo.add(act);
                        }
                        // log.warning(actividades.size() + "");
                        for(dataActividad act : foo){
                            if(!act.getDtCategorias().contains(categoria)){
                                actividades.remove(act);
                            }
                        }
                    }
                }
            }
        }else{
            if(!categoria.equals("")){
                actividades = IAC.getAllActividades();
                List<dataActividad> foo = new ArrayList<dataActividad>();;
                for(dataActividad act : actividades){
                    foo.add(act);
                }

                // log.warning(actividades.size() + "");
                for(dataActividad act : foo){
                    if(!act.getDtCategorias().contains(categoria)){
                        actividades.remove(act);
                    }
                }
            } 
        }

        if(actividades.isEmpty()){
            return null;
        }else{
            List<dataSalida> salidas = new ArrayList<dataSalida>();
            List<dataSalida> tmp = new ArrayList<dataSalida>();
            for(dataActividad act : actividades){
                tmp = act.getDtSalidas();
                for(dataSalida salida : tmp){
                    if(!salidas.contains(salida)){
                        salidas.add(salida);
                    }
                }
            }

            return salidas;
        }
    }
}

