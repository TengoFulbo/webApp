package turismouy.webapp;

import java.io.IOException;
import java.io.InputStream;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.entidades.imagen;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.utilidades.log;

@WebServlet("/miCuenta")
@MultipartConfig
public class MiCuentaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén el nickname del usuario de la URL
        String nickname = request.getParameter("nickname");

        request.setAttribute("pageTitle", "Cuenta - TurismoUY");

        if (nickname != null) {
            IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();

            dataUsuario usuario = null;
            try {
                usuario = IUC.mostrarInfo(nickname);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            request.setAttribute("user", usuario);
        } else {
            HttpSession session = request.getSession();
            request.setAttribute("user", session.getAttribute("dataUsuario"));            
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/miCuenta.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Part filePart = request.getPart("file");

        byte[] imageData = new byte[(int) filePart.getSize()];
        try (InputStream input = filePart.getInputStream()) {
            input.read(imageData);
        }

        imagen imagen = new imagen();
        imagen.setData(imageData);

        String nickname                 = request.getParameter("nickname");
        String nombre                   = request.getParameter("nombre");
        String apellido                 = request.getParameter("apellido");
        String nacimiento               = request.getParameter("nacimiento");
        String email                    = request.getParameter("email");
        String descripcion              = request.getParameter("descripcion");
        String url                      = request.getParameter("url");
        String nacionalidad             = request.getParameter("nacionalidad");

        // log.info("--------------------------------------------------------------");
        // System.out.println("Nickname: " + nickname);
        // System.out.println("Nombre: " + nombre);
        // System.out.println("Apellido: " + apellido);
        // System.out.println("Email: " + email);
        // System.out.println("Nacimiento: " + nacimiento);
        // System.out.println("Descripción: " + descripcion);
        // System.out.println("URL: " + url);
        // System.out.println("Nacionalidad: " + nacionalidad);
        // System.out.println("Imagen: " + imageData);
        // log.info("--------------------------------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fechaLocalDate = LocalDate.parse(nacimiento, formatter);

        IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
        
        if (nacionalidad != null){
            try {
                IUC.modificarUsuario(nickname, nombre, apellido, fechaLocalDate, imageData);
                log.info("  -> Usuario Modificado <-");
            } catch(Exception e) {
                log.warning("[ERROR AL MODIFICAR TURISTA]");
            }
        } else if (descripcion != null || url != null) {
            try {
                IUC.modificarUsuario(nickname, nombre, apellido, fechaLocalDate);
                log.info("  -> Usuario Modificado <-");
            } catch(Exception e) {
                log.warning("[ERROR AL MODIFICAR PROVEEDOR]");
            }
        }

        // dataUsuario usuario;
        // try {
        //     usuario = IUC.mostrarInfo(nickname);
        //     session.removeAttribute("dataUsuario");
        //     session.setAttribute("dataUsuario", usuario);
        // } catch (UsuarioNoExisteExcepcion e) {
        //     System.out.println("Error al mostrar el usuario.");
        //     e.printStackTrace();
        // }

        response.sendRedirect(request.getContextPath() + "/home");
        log.info("Successfully");
    }
}
