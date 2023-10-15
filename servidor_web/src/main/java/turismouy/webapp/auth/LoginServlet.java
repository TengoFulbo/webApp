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

        if (IUC.login(username, password)) {

            // Creamos la sesión.
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirigimos al home.
            response.sendRedirect(request.getContextPath() + "/home");
        }


        // Validar las credenciales (esto puede ser reemplazado por una lógica más robusta)
        if (username.equals("Ezequiel") && password.equals("1234")) {
            // Crear una sesión HttpSession y almacenar información de usuario
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirigir a la página protegida
            response.sendRedirect(request.getContextPath() + "/home");
            // request.getRequestDispatcher("/WEB-INF/hola.jsp").forward(request, response);
        } else {
            // Si las credenciales son incorrectas, redirigir de vuelta al formulario de inicio de sesión
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            // response.sendRedirect(request.getContextPath() + "/WEB-INF/login.jsp");
        }
    }
}
