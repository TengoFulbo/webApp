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

import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.departamento;
import turismouy.svcentral.entidades.departamento;
import turismouy.svcentral.entidades.categoria;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.utilidades.log;
import turismouy.webapp.utils.LocalDateAdapter;

@WebServlet("/homeSalidas")
public class HomeSalidas extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", "Salidas - TurismoUY");

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


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/homeSalidas.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gson gson = new Gson();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        IActividadController IAC = Fabrica.getInstance().getIActividadController();
        ISalidaController ISC = Fabrica.getInstance().getISalidaController();

        String categoria    = request.getParameter("categoria");
        String departamento = request.getParameter("departamento");

        log.info("Departamento: " + departamento);

        log.info("[homeActividades Post]");

        log.warning("Entro aca klok");

        List<dataSalida> salidasList = ISC.getAllSalidas();

        List<dataSalida> salEliminar = filtroDC(departamento, categoria);

        // Elimina los elementos de la lista original
        salidasList.removeAll(salEliminar);

        if (salidasList != null) {
            for (dataSalida salida : salidasList){
                log.info(salida.getNombre());
            }
        }

        request.setCharacterEncoding("UTF-8");
        String resultadoJson = gson.toJson(salidasList);
        response.setContentType("application/json");
        response.getWriter().write(resultadoJson);
    }

    List<dataSalida> filtroDC(String departamento, String categoria){

        IActividadController IAC = Fabrica.getInstance().getIActividadController();
        ISalidaController ISC = Fabrica.getInstance().getISalidaController();
        IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
        ICategoriaController ICC = Fabrica.getInstance().getICategoriaController();

        List<dataDepartamento> departamentos = IDC.listarDepartamentos();
        List<dataActividad> actividades = new ArrayList<dataActividad>();
        List<dataCategoria> categorias = ICC.listarCategorias();

        if (!departamento.equals("")){
            for(dataDepartamento dpto : departamentos){
                if(dpto.getNombre().equals(departamento)){
                    actividades = dpto.getActividades();
                    
                    if(!categoria.equals("")){
                        for(dataActividad act : actividades){
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
                for(dataActividad act : actividades){
                    if(!act.getDtCategorias.contains(categoria)){
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
                tmp = act.getAllSalidas();
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

