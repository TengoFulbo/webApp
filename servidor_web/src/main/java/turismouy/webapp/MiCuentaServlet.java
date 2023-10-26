package turismouy.webapp;

import java.io.IOException;
import java.io.InputStream;

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
import turismouy.svcentral.interfaces.IUsuarioController;

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
                // usuario.getActividades().isEmpty()
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
        Part filePart = request.getPart("file");

        byte[] imageData = new byte[(int) filePart.getSize()];
        try (InputStream input = filePart.getInputStream()) {
            input.read(imageData);
        }

        IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();

        // IUC.
        // System.out.println(imageData);
        // imagen userImage = new imagen();
        // userImage.setUser(user);
        // userImage.setImageData(imageData);
        
        response.setStatus(HttpServletResponse.SC_OK); // Indicar que la carga se completó con éxito
        System.out.println("HOla");
    }
}
