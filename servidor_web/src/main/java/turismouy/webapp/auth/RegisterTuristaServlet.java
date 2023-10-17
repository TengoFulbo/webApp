package turismouy.webapp.auth;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.interfaces.IUsuarioController;

@WebServlet("/registerTurista")
public class RegisterTuristaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nickname = null;
        String nombreyapellido = null;
        String email = null;
        String fechaN = null;
        String password = null;
        LocalDate fecha = null;
        String nombre = null;
        String apellido = null;
        String nacionalidad = null; // Requiere turista.

        nickname        = request.getParameter("nickname");
        // nombreyapellido = request.getParameter("nombreyapellido");
        nombre          = request.getParameter("nombre");
        apellido        = request.getParameter("apellido");
        email           = request.getParameter("email");
        fechaN          = request.getParameter("fechaN");
        nacionalidad    = request.getParameter("nacionalidad");
        password        = request.getParameter("password");

        try {
            // Parsear la cadena en un objeto LocalDate
            fecha = LocalDate.parse(fechaN, formatoFecha);
        } catch (DateTimeParseException e) {
            // Manejar errores de formato de fecha aquí
            System.out.println(e);
        }

        // // Validar que el campo contenga un solo espacio como separador
        // if (nombreyapellido != null && nombreyapellido.contains(" ") && nombreyapellido.indexOf(" ") == nombreyapellido.lastIndexOf(" ")) {
        //     // Separar el nombre y el apellido
        //     String[] partes = nombreyapellido.split(" ");

        //     if (partes.length == 2) {
        //         nombre = partes[0];
        //         apellido = partes[1];
        //     } else {
        //         // El campo no tiene exactamente un espacio como separador
        //         // Manejar el error o realizar otra acción
        //         // TODO: falta manejar el error.
        //     }
        // } else {
        //     // No se encontró un espacio o se encontraron múltiples espacios en el campo
        //     // Manejar el error o realizar otra acción
        //     // TODO: falta manejar el error.
        // }

        System.out.println("Nickname: " + nickname);
        // System.out.println("Nombreyapellido: " + nombreyapellido);
        System.out.println("fechaN: " + fechaN);
        System.out.println("Email: " + email);
        System.out.println("password: " + password);
        System.out.println("fecha: " + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear());
        System.out.println("nombre: " + nombre);
        System.out.println("apellido: " + apellido);
        System.out.println("nacionalidad: " + nacionalidad);

        IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();

        try {
            IUC.registrarTurista(nickname, nombre, apellido, email, nacionalidad, fecha, password);

            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        response.sendRedirect(request.getContextPath() + "/register");
    }
}