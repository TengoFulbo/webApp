package turismouy.webapp.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// import turismouy.svcentral.interfaces.IUsuarioController;
// import turismouy.svcentral.Fabrica;
// import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.svcentral.middlewares.DataUsuario;
import turismouy.webapp.utils.log;

@WebServlet("/homeUsuarios")
public class HomeUsuarios extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", "Usuarios - TurismoUY");

        // List<dataUsuario> usuarios = new ArrayList<dataUsuario>();
        
        // IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
        Publicador API = new PublicadorService().getPublicadorPort();
        
        List<DataUsuario> usuarios = API.usuarioListarUsuarios();

        request.setAttribute("usuarios", usuarios);

        // Muestra la pag de homeUsuarios
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/homeUsuarios.jsp");
        dispatcher.forward(request, response);
    }
}
