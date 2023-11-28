package turismouy.webapp;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.google.gson.Gson;

//import turismouy.svcentral.interfaces.IActividadController;
//import turismouy.svcentral.interfaces.IPaqueteController;
//import turismouy.svcentral.Fabrica;
//import turismouy.svcentral.datatypes.dataActividad;
//import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.webapp.utils.log;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.svcentral.middlewares.DataActividad;
import turismouy.svcentral.middlewares.DataPaquete;


@WebServlet("/autoCompleteServ")
public class AutoCompleteServlet extends HttpServlet {
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener datos desde alguna fuente (por ejemplo, una base de datos o servicio)
        List<String> datos = obtenerDatos();

        // Convertir la lista a formato JSON
        Gson gson = new Gson();
        String datosJson = gson.toJson(datos);

        // Configurar la respuesta del servlet
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(datosJson);
    }

    private List<String> obtenerDatos() {
        List<String> datos = new ArrayList<>();

        //IActividadController IAC = Fabrica.getInstance().getIActividadController();
        Publicador API = new PublicadorService().getPublicadorPort();
        
        List<DataActividad> listaActividad = API.actividadGetAllActividades();  
    	if (!listaActividad.isEmpty() && listaActividad != null) {
    		for(DataActividad act : listaActividad) {
        		datos.add(act.getNombre());
        	}
    	}
    	
    	//IPaqueteController IPC = Fabrica.getInstance().getIPaqueteController();
    	
    	List<DataPaquete> listaPaquete = API.paqueteGetAllPaquetes();
    	if (!listaPaquete.isEmpty() && listaPaquete != null) {
    		for(DataPaquete paq : listaPaquete) {
        		datos.add(paq.getNombre());
        	}
    	}
        
        return datos;
    }
}
