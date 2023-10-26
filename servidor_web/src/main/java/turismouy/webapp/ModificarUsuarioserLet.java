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

import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.utilidades.log;

@WebServlet("/modificarUsuario")
public class ModificarUsuarioserLet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", "Cuenta - TurismoUY");


        // Muestra el formulario de inicio de sesión
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/miCuenta.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String descripcion = request.getParameter("descripcion");
        String url = request.getParameter("url");
        String nacionalidad = request.getParameter("nacionalidad");

        log.info("--------------------------------------------------------------");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Descripción: " + descripcion);
        System.out.println("URL: " + url);
        System.out.println("Nacionalidad: " + nacionalidad);
        log.info("--------------------------------------------------------------");

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        // LocalDate fechaSalida = LocalDate.parse(fechaSalidaString, formatter);

        // ISalidaController ISC = Fabrica.getInstance().getISalidaController();

        // try {
        //     // void crearSalida(String nombre, int capacidad, LocalDate fechaAlta, LocalDate fechaSalida, String lugarSalida, String nombreActividad)
        //     ISC.crearSalida(nombreSalida, capacidad, LocalDate.now(), fechaSalida, lugarSalida, nombreActividad);
        // }catch (Exception e){
        //     log.error("[AltaSalidaServlet] Error al crear salida");
        // }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/miCuenta.jsp");
        dispatcher.forward(request, response);
    }
}
