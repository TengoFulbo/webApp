package turismouy.webapp.auth;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;

import turismouy.svcentral.Fabrica;
import turismouy.svcentral.interfaces.IUsuarioController;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", "Registro - TurismoUY");

        // Muestra el formulario de inicio de sesión
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/register.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        String isProveedor = request.getParameter("isProveedor");
        String nickname = null;
        String nombreyapellido = null;
        String email = null;
        String fechaN = null;
        String password = null;
        LocalDate fecha = null;
        String nombre = null;
        String apellido = null;
        String desc = null;         // Requiere proveedor.
        String url = null;          // Requiere proveedor.
        String nacionalidad = null; // Requiere turista.

        if (isProveedor != null && isProveedor == "true") {
            nickname = request.getParameter("pNickname");
            nombreyapellido = request.getParameter("pNombreyapellido");
            email = request.getParameter("pEmail");
            fechaN = request.getParameter("pFechaN");
            desc = request.getParameter("pDesc");
            desc = request.getParameter("pUrl");
            password = request.getParameter("pPassword");
        } else {
            nickname = request.getParameter("tNickname");
            nombreyapellido = request.getParameter("tNombreyapellido");
            email = request.getParameter("tEmail");
            fechaN = request.getParameter("tFechaN");
            nacionalidad = request.getParameter("tNacionalidad");
            password= request.getParameter("tPassword");   
        }

        System.out.println("isProveedor: " + isProveedor);
        System.out.println("Nickname: " + nickname);
        System.out.println("fechaN: " + fechaN);
        System.out.println("Email: " + email);

        try {
            // Parsear la cadena en un objeto LocalDate
            fecha = LocalDate.parse(fechaN, formatoFecha);
        } catch (DateTimeParseException e) {
            // Manejar errores de formato de fecha aquí
            System.out.println(e);
        }


        // Validar que el campo contenga un solo espacio como separador
        if (nombreyapellido != null && nombreyapellido.contains(" ") && nombreyapellido.indexOf(" ") == nombreyapellido.lastIndexOf(" ")) {
            // Separar el nombre y el apellido
            String[] partes = nombreyapellido.split(" ");

            if (partes.length == 2) {
                nombre = partes[0];
                apellido = partes[1];
            } else {
                // El campo no tiene exactamente un espacio como separador
                // Manejar el error o realizar otra acción
                // TODO: falta manejar el error.
            }
        } else {
            // No se encontró un espacio o se encontraron múltiples espacios en el campo
            // Manejar el error o realizar otra acción
            // TODO: falta manejar el error.
        }

        
        IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();

        try {
            if (isProveedor != null && isProveedor == "true") {
                // Si el formulario es de proveedor entra acá
                System.out.println("Hola proveedor");

                IUC.registrarProveedor(nickname, nombre, apellido, email, desc, url, fecha, password);
            } else {
                // Si el formulario es de turista entra acá
                System.out.println("Hola turista");

                IUC.registrarTurista(nickname, nombre, apellido, email, nacionalidad, fecha, password);
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }
    }
}
