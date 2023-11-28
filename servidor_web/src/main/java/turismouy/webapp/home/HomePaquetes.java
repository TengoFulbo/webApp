package turismouy.webapp.home;

import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turismouy.svcentral.middlewares.DataPaquete;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.webapp.utils.log;
import turismouy.webapp.utils.LocalDateAdapter;

import java.util.List;

@WebServlet("/homePaquetes")
public class HomePaquetes extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageTitle", "Paquetes - TurismoUY");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/homePaquetes.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        log.info("[HomePaquetes Post]");

        Publicador API = new PublicadorService().getPublicadorPort();

        List<DataPaquete> paquetes = API.paqueteGetAllPaquetes();

        request.setCharacterEncoding("UTF-8");
        String resultadoJson = gson.toJson(paquetes);
        response.setContentType("application/json");
        response.getWriter().write(resultadoJson);
    }
}
