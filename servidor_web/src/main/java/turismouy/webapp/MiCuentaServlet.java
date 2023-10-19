package turismouy.webapp;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.interfaces.IUsuarioController;

@WebServlet("/miCuenta")
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
}
