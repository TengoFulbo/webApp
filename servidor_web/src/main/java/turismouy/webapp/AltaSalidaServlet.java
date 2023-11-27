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

import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
//import turismouy.svcentral.interfaces.IUsuarioController;
//import turismouy.svcentral.interfaces.ISalidaController;
//import turismouy.svcentral.Fabrica;
//import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.webapp.utils.log;

@WebServlet("/altaSalida")
public class AltaSalidaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", "Mis Salidas - TurismoUY");


        // Muestra el formulario de inicio de sesión
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/misSalidas.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String nombreActividad = request.getParameter("actividadSelect");
        String departamentoSelect = request.getParameter("departamentoSelect");
        String nombreSalida = request.getParameter("nombre");
        String lugarSalida = request.getParameter("lugarSalida");
        String fechaSalidaString = request.getParameter("fechaSalidaString");
        int capacidad = Integer.parseInt(request.getParameter("capacidad"));

        log.info("--------------------------------------------------------------");
        System.out.println("Actividad seleccionada: " + nombreActividad);
        System.out.println("Departamento seleccionado: " + departamentoSelect);
        System.out.println("Nombre: " + nombreSalida);
        System.out.println("Lugar de Salida: " + lugarSalida);
        System.out.println("Fecha de Salida: " + fechaSalidaString);
        System.out.println("Capacidad: " + capacidad);
        log.info("--------------------------------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        LocalDate fechaSalida = LocalDate.parse(fechaSalidaString, formatter);

        //ISalidaController ISC = Fabrica.getInstance().getISalidaController();
        Publicador API = new PublicadorService().getPublicadorPort();
        //API.salidaCrearSalida(nombreSalida, capacidad, LocalDate.now(), fechaSalida, lugarSalida, nombreActividad);
        try {
            // void crearSalida(String nombre, int capacidad, LocalDate fechaAlta, LocalDate fechaSalida, String lugarSalida, String nombreActividad)
        	//ISC.crearSalida(nombreSalida, capacidad, LocalDate.now(), fechaSalida, lugarSalida, nombreActividad);
        }catch (Exception e){
            log.error("[AltaSalidaServlet] Error al crear salida");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/misSalidas.jsp");
        dispatcher.forward(request, response);
    }
}
