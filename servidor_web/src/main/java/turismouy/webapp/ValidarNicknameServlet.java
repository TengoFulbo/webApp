package turismouy.webapp;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// import turismouy.svcentral.Fabrica;
// import turismouy.svcentral.interfaces.IUsuarioController;
// import turismouy.svcentral.datatypes.dataUsuario;
// import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.middlewares.DataUsuario;
import turismouy.svcentral.middlewares.NoExisteExcepcion_Exception;



@WebServlet("/validarNickname")
public class ValidarNicknameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String valorInput = request.getParameter("valor");

        System.out.println("Valor recibido desde la solicitud AJAX: " + valorInput);

        // IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
        Publicador API = new PublicadorService().getPublicadorPort();
        
        DataUsuario tmp = null;
        try {
        	tmp = API.usuarioMostrarInfo(valorInput);
        } catch (NoExisteExcepcion_Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

        // Crear una clase para la respuesta
        RespuestaValidacion respuesta = new RespuestaValidacion();
        if (tmp == null) {
            respuesta.setValido(true);
        } else {
            respuesta.setValido(false);
        }

        // Convertir la respuesta a JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(respuesta);

        // Configurar la respuesta del servlet
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    // Clase para representar la respuesta en JSON
    private static class RespuestaValidacion {
        private boolean valido;

        public boolean isValido() {
            return valido;
        }

        public void setValido(boolean valido) {
            this.valido = valido;
        }
    }
}
