package turismouy.webapp.auth;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import turismouy.svcentral.middlewares.DataUsuario;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
//import turismouy.svcentral.interfaces.IUsuarioController;
//import turismouy.svcentral.Fabrica;
//import turismouy.svcentral.datatypes.dataUsuario;
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

        HttpSession session = request.getSession();

        // Validaciones sobre parametros.
        if (!validarTexto(username, 1) || !validarTexto(password, 1)) {
            session.setAttribute("errorLogin", "El usuario / contraseña no son correctos.");

            log.error("Parametros invalidos.");
            response.sendRedirect(request.getContextPath() + "/login");
            return; 
        }


        //IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
        
        // Llamamos al web service.
        Publicador API = new PublicadorService().getPublicadorPort();
        
        if (API.usuarioLogin(username, password)) {

            // Creamos la sesión.
            session.setAttribute("username", username);

            try {
                log.info("Antes Username: "+ username);
                log.info("Password: "+ password);
                //dataUsuario usuario = IUC.mostrarInfo(username);
                DataUsuario usuario = API.usuarioMostrarInfo(username);
                log.info("Despues Username: "+ username);
                log.info("Password: "+ password);
                session.setAttribute("dataUsuario", usuario);
                System.out.println(usuario.getNombre());
            } catch (Exception e) {
                log.error("[LoginServlet] Error con el usuario");
                e.printStackTrace();
            }

            // Redirigimos al home.
            response.sendRedirect(request.getContextPath() + "/home");
            // request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        } else {

            session.setAttribute("errorLogin", "El usuario / contraseña no son correctos.");
            log.error("El usuario es incorrecto.");
            // request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    /*
        * Level 1: Textos simples. Valida que no sea vacio, que no empiece o termine con espacio y que al menos tenga 1 letra o número.
        * Level 2: Correos. Valida lo mismo que el nivel 1, además valida que contenga una sola @ para los correos.
        * Livel 3: Nicknames. Valida lo mismo que el nivel 1, además valida que no contenga espacios. Sirve para nombres de usuario.
    */
    private static boolean validarTexto(String texto, int nivel) {
        switch (nivel) {
            case 1:
                return validarNivel1(texto);
            case 2:
                return validarNivel1(texto) && contarCaracter(texto, '@') == 1;
            case 3:
                return validarNivel1(texto) && !texto.contains(" ");
            default:
                return false;
        }
    }

    private static boolean validarNivel1(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        if (texto.trim().length() != texto.length()) {
            return false;
        }
        return texto.matches(".*[a-zA-Z0-9].*");
    }

    private static int contarCaracter(String texto, char caracter) {
        int count = 0;
        for (char c : texto.toCharArray()) {
            if (c == caracter) {
                count++;
            }
        }
        return count;
    }
}
