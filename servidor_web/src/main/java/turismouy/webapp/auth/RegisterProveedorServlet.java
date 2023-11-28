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
import jakarta.servlet.http.HttpSession;
//import turismouy.svcentral.Fabrica;
//import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;

@WebServlet("/registerProveedor")
public class RegisterProveedorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        HttpSession session = request.getSession();

        String nickname = null;
        String email = null;
        String fechaN = null;
        String password = null;
        LocalDate fecha = null;
        String nombre = null;
        String apellido = null;
        String desc = null;         // Requiere proveedor.
        String url = null;          // Requiere proveedor.

        // nombreyapellido = request.getParameter("nombreyapellido");
        nickname        = request.getParameter("nickname");
        nombre          = request.getParameter("nombreP");   
        apellido        = request.getParameter("apellidoP");   
        email           = request.getParameter("email");
        fechaN          = request.getParameter("fechaNP");
        desc            = request.getParameter("descP");
        url             = request.getParameter("urlP");
        password        = request.getParameter("passwordP");

        if (nickname == null || nickname == "" ||
            nombre == null || nombre == "" ||
            apellido == null || apellido == "" ||
            email == null || apellido == "" ||
            fechaN == null || fechaN == "" ||
            desc == null || desc == "" ||
            url == null || url == "" ||
            password == null || password == "")
        {
            System.out.println("[RegisterTurista] Error: Parametros invalidos.");
            session.setAttribute("errorLogin", "Parametros invalidos.");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        try {
            // Parsear la cadena en un objeto LocalDate
            fecha = LocalDate.parse(fechaN, formatoFecha);
        } catch (DateTimeParseException e) {
            // Manejar errores de formato de fecha aquí
            System.out.println(e);
        }

        System.out.println("Nickname: " + nickname);
        // // System.out.println("Nombreyapellido: " + nombreyapellido);
        System.out.println("fechaN: " + fechaN);
        System.out.println("Email: " + email);
        System.out.println("password: " + password);
        System.out.println("fecha: " + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear());
        System.out.println("nombre: " + nombre);
        System.out.println("apellido: " + apellido);
        System.out.println("desc: " + desc);
        System.out.println("url: " + url);

        //IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
        Publicador API = new PublicadorService().getPublicadorPort();
        
        try {
            //IUC.registrarProveedor(nickname, nombre, apellido, email, desc, url, fecha, password);
        	API.usuarioRegistrarProveedorPassword(nickname, nombre, apellido, email, desc, url, fecha.toString(), password);
            session.setAttribute("errorLogin", "Usuario registrado. Ahora puedes iniciar sesión");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        response.sendRedirect(request.getContextPath() + "/register");
    }
}