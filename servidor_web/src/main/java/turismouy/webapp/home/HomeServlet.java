package turismouy.webapp.home;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import turismouy.svcentral.middlewares.controladores.HoraWebService;
import turismouy.svcentral.middlewares.controladores.HoraWebServiceService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        // session.setAttribute("username", username);
        String usuario = (String) session.getAttribute("username");

        request.setAttribute("pageTitle", "Home - TurismoUY");

        request.setAttribute("usuario", usuario);
        request.setAttribute("correo", "prueba@gmail.com");
        request.setAttribute("salidas", "5");
        request.setAttribute("compras", "12");
        request.setAttribute("actividades", "100");

        // Crea una instancia del servicio cliente
        HoraWebService cliente = new HoraWebServiceService().getHoraWebServicePort();

        System.out.println("Hola....");

        // Llama al servicio
        String resultado = cliente.obtenerHoraActual();
        System.out.println("Resultado del web service: " + resultado);

        // Crea una instancia del servicio        MonitoreoWebService service = new MonitoreoWebService();

        // Obtén un puerto para la comunicación
        // MonitoreoWeb port = service.getMonitoreoWebPort();

        // MonitoreoWeb monitor = new MonitoreoWeb()

        // Redireciona
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }
}
