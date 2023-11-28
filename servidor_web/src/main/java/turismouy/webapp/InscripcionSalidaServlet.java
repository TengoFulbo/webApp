package turismouy.webapp;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.interfaces.IInscripcionController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.utilidades.log;

@WebServlet("/insSalida")
public class InscripcionSalidaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", "Mis Salidas - TurismoUY");


        // Muestra el formulario de inicio de sesión
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/misSalidas.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        log.warning("      /// Entro al post ///");
        HttpSession session = request.getSession();

        dataUsuario usuario = (dataUsuario) session.getAttribute("dataUsuario");
        String datosInscripcion = (String) request.getSession().getAttribute("datosInscripcion");

        
        int cantidadIns = Integer.parseInt(request.getParameter("cantidadIns"));
        String formaPago = request.getParameter("formaPago");
        String nSalida = request.getParameter("nombreSalida");

        if (formaPago != null) {
            if (formaPago.equals("general")) {
                log.info("General 🍆🍆");
            } else if (formaPago.equals("paquete")) {
                log.info("Paquete 😩😩");
            } 
        }else{
            log.warning("   [Invalid Radio]");
        }

        log.info("--------------------------------------------------------------");
        System.out.println("Cantidad de personas a inscribirse: " + cantidadIns);
        System.out.println("Forma de Pago seleccionada: " + formaPago);
        System.out.println("Usuario nickname: " + usuario.getNickname());
        System.out.println("Nombre de la salida: " + nSalida);
        log.info("--------------------------------------------------------------");
        // public abstract void crearInscripcion(LocalDate fecha, int cant, String nombreTursita, String nombreSalida, String nombreAct
        ISalidaController ISC = Fabrica.getInstance().getISalidaController();
        IInscripcionController IIC = Fabrica.getInstance().getIInscripcionController();
        LocalDate fecha = LocalDate.now();
        
        dataSalida salida = ISC.mostrarDatosSalida(nSalida);
        List<dataActividad> listActividad = salida.getActividades();
        
        String actividadNombre = "";
        
        if(listActividad != null) {
        	for(dataActividad act : listActividad) {
        		actividadNombre = act.getNombre();
        	}
        }

        try {
			IIC.crearInscripcion(fecha, cantidadIns, usuario.getNickname(), nSalida, actividadNombre);
		} catch (ParametrosInvalidosExcepcion e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (UsuarioYaExisteExcepcion e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (UsuarioNoExisteExcepcion e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}



        // try {
        //     // void crearSalida(String nombre, int capacidad, LocalDate fechaAlta, LocalDate fechaSalida, String lugarSalida, String nombreActividad)
        //     ISC.crearSalida(nombreSalida, capacidad, LocalDate.now(), fechaSalida, lugarSalida, nombreActividad);
        // }catch (Exception e){
        //     log.error("[AltaSalidaServlet] Error al crear salida");
        // }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/misSalidas.jsp");
        dispatcher.forward(request, response);
    }
}
