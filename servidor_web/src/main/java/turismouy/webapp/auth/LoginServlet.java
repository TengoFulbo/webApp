package turismouy.webapp.auth;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.utilidades.log;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", "Login - TurismoUY");


        // Muestra el formulario de inicio de sesión
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
        
        HttpSession session = request.getSession();

        if (IUC.login(username, password)) {

            // Creamos la sesión.
            session.setAttribute("username", username);

            try {
                dataUsuario usuario = IUC.mostrarInfo(username);
                session.setAttribute("dataUsuario", usuario);
                System.out.println(usuario.getNombre());
                // TODO: ⚠️ Agregar o modificar "mostrarInfo" para que también pueda recibir un correo. 
            } catch (Exception e) {
                log.error("[LoginServlet] Error con el usuario");
            }

            // Redirigimos al home.
            response.sendRedirect(request.getContextPath() + "/home");
            // request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        } else {

            session.setAttribute("errorLogin", "El usuario / contraseña no son correctos.");
            log.error("El usuario es incorrecto.");
            // request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/login");
            // request
        }


        // // Validar las credenciales (esto puede ser reemplazado por una lógica más robusta)
        // if (username.equals("Ezequiel") && password.equals("1234")) {
        //     // Crear una sesión HttpSession y almacenar información de usuario
        //     HttpSession session = request.getSession();
        //     session.setAttribute("username", username);

        //     // Redirigir a la página protegida
        //     response.sendRedirect(request.getContextPath() + "/home");
        //     // request.getRequestDispatcher("/WEB-INF/hola.jsp").forward(request, response);
        // } else {
        //     // Si las credenciales son incorrectas, redirigir de vuelta al formulario de inicio de sesión
        //     request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        //     // response.sendRedirect(request.getContextPath() + "/WEB-INF/login.jsp");
        // }
    }
}
