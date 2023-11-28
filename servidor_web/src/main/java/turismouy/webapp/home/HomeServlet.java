package turismouy.webapp.home;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
// import turismouy.svcentral.controladores.DataDepartamento;
// import turismouy.svcentral.controladores.ActividadController;
// import turismouy.svcentral.controladores.ActividadControllerService;
import turismouy.svcentral.controladores.DepartamentoController;
// import turismouy.svcentral.controladores.DepartamentoControllerService;
// import turismouy.svcentral.middlewares.controladores.ActividadService;
// import turismouy.svcentral.middlewares.controladores.HoraWebService;
// import turismouy.svcentral.middlewares.controladores.HoraWebServiceService;
// import turismouy.svcentral.middlewares.controladores.ActividadService;
// import turismouy.svcentral.middlewares.controladores.ActividadServiceService;
// import turismouy.svcentral.middlewares.interfaces.IActividadService;
// import turismouy.svcentral.middlewares.interfaces.IActividadService;
// import turismouy.svcentral.middlewares.controladores.HoraWebService;
// import turismouy.svcentral.middlewares.controladores.HoraWebServiceService;
// import turismouy.svcentral.controladores.DepartamentoControllerService;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.middlewares.DataSalida;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;

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
        
        Publicador API = new PublicadorService().getPublicadorPort();
        List<DataSalida> salidas = API.salidaGetAllSalidas();
        
        for (DataSalida salida : salidas) {
        	System.out.println("Salida: " + salida.getNombre());
        }


        // DepartamentoController cliente = new DepartamentoControllerService().getDepartamentoControllerPort();


        // List<> = cliente.listarDepartamentos();

        // ActividadController cliente = new ActividadControllerService().getActividadControllerPort();
        
        // ActividadService cliente = new ActividadServiceService().getActividadServicePort();

        
        // System.out.println("Ejecutando el serivicio web...");
        // List<String> listString = new ArrayList<String>();
        // 
        // cliente.crearActividad("a", "b", "c", "d", 1, 2, "e", listString);
        

        // TODO: Asi se consume un serivico web 
        // Asi se consume un servicio web
        // // Crea una instancia del servicio cliente
        // HoraWebService cliente = new HoraWebServiceService().getHoraWebServicePort();





        // DepartamentoController DC = new DepartamentoControllerService().getDepartamentoControllerPort();

        // List<DataDepartamento> departamentos = DC.listarDepartamentos();

        // System.out.println("Imprimiendo los departamentos...");
        // for (DataDepartamento departamento : departamentos) {
        //     System.out.println("Departamento: " + departamento.getNombre());
        // }




        
        System.out.println("Hola...."); 

        // // Llama al servicio
        // String resultado = cliente.obtenerHoraActual();
        // System.out.println("Resultado del web service: " + resultado);

        // Redireciona
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }
}
