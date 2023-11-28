package turismouy.webapp.home;
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

// import turismouy.svcentral.interfaces.IActividadController;
// import turismouy.svcentral.interfaces.ICategoriaController;
// import turismouy.svcentral.interfaces.IDepartamentoController;
// import turismouy.svcentral.interfaces.ISalidaController;
// import turismouy.svcentral.Fabrica;
// import turismouy.svcentral.datatypes.dataCategoria;
// import turismouy.svcentral.datatypes.dataActividad;
// import turismouy.svcentral.entidades.actividad;
// import turismouy.svcentral.entidades.departamento;
// import turismouy.svcentral.entidades.departamento;
// import turismouy.svcentral.entidades.categoria;
// import turismouy.svcentral.datatypes.dataSalida;
// import turismouy.svcentral.datatypes.dataDepartamento;
//import turismouy.webapp.utils.log;
import turismouy.webapp.utils.log;
import turismouy.svcentral.middlewares.DataActividad;
import turismouy.svcentral.middlewares.DataCategoria;
import turismouy.svcentral.middlewares.DataDepartamento;
import turismouy.svcentral.middlewares.DataSalida;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.webapp.utils.LocalDateAdapter;

@WebServlet("/homeSalidas")
public class HomeSalidas extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", "Salidas - TurismoUY");

//        IActividadController IAC = Fabrica.getInstance().getIActividadController();
//        IDepartamentoController IAD = Fabrica.getInstance().getIDepartamentoController();
//        ICategoriaController ICC = Fabrica.getInstance().getICategoriaController();
//        ISalidaController ISC = Fabrica.getInstance().getISalidaController();
        Publicador API = new PublicadorService().getPublicadorPort();

//        List<dataActividad> actividadesList = IAC.getAllActividades();
        List<DataActividad> actividadesList = API.actividadGetAllActividades();
    
//        List<dataDepartamento> departamentos = IAD.listarDepartamentos();
        List<DataDepartamento> departamentos = API.departamentoListarDepartamentos();
        
//        List<dataCategoria> categorias = ICC.listarCategorias();
        List<DataCategoria> categorias = API.categoriaListarCategorias();

        System.out.println("Hola XML");
        List<DataSalida> salidas = API.salidaListarSalidas();
        System.out.println("Chau XML");


        request.setAttribute("actividades", actividadesList);
        request.setAttribute("departamentos", departamentos);
        request.setAttribute("categorias", categorias);
        request.setAttribute("salidas", salidas);


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/homeSalidas.jsp");
        dispatcher.forward(request, response);
    }
    
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gson gson = new Gson();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        //Publicador API = new PublicadorService().getPublicadorPort();

//        IActividadController IAC = Fabrica.getInstance().getIActividadController();
//        ISalidaController ISC = Fabrica.getInstance().getISalidaController();

        String categoria    = request.getParameter("categoria");
        String departamento = request.getParameter("departamento");
        String actividad    = request.getParameter("actividad");

        log.info("Departamento: " + departamento);

        log.info("[homeActividades Post]");

        log.warning("Entro aca klok");

        List<DataSalida> salidas = null;

        if (!actividad.equals("")){
            salidas = filtroA(actividad);
            log.info("Ordeno por actividad");
        }else{
            salidas = filtroDC(departamento, categoria);
            log.info("Ordeno por general");
        }

        // if (salidas != null) {
        //     for (dataSalida salida : salidas){
        //         log.info(salida.getNombre());
        //     }
        // }

        request.setCharacterEncoding("UTF-8");
        String resultadoJson = gson.toJson(salidas);
        response.setContentType("application/json");
        response.getWriter().write(resultadoJson);
    }

    List<DataSalida> filtroA(String actividadName){
//    	IActividadController IAC = Fabrica.getInstance().getIActividadController();
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

//        IActividadController IAC = Fabrica.getInstance().getIActividadController();
//        ISalidaController ISC = Fabrica.getInstance().getISalidaController();
//        IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
//        ICategoriaController ICC = Fabrica.getInstance().getICategoriaController();
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

