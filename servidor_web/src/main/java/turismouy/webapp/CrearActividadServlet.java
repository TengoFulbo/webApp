package turismouy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.middlewares.DataActividad;
import turismouy.svcentral.middlewares.DataCategoria;
import turismouy.svcentral.middlewares.DataUsuario;
//import turismouy.svcentral.datatypes.dataActividad;
//import turismouy.svcentral.datatypes.dataCategoria;
//import turismouy.svcentral.datatypes.dataUsuario;
//import turismouy.svcentral.interfaces.IActividadController;
//import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.webapp.utils.LocalDateAdapter;

@WebServlet("/crearActividad")
public class CrearActividadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String departamento = request.getParameter("departamento");
        
        Publicador API = new PublicadorService().getPublicadorPort();
        
        List<DataActividad> actividadesList = null;

        if (departamento != null) {
            actividadesList = API.actividadGetAllActividadesDepartamento(departamento);
            if (actividadesList == null) {
                System.out.println("[crearActividades] No hay actividades");
                // response.getWriter().write("");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }
        List<DataCategoria> categorias = API.categoriaListarCategorias();

        JsonObject respuesta = new JsonObject();
        respuesta.add("actividades", gson.toJsonTree(actividadesList));
        respuesta.add("categorias", gson.toJsonTree(categorias));
        
        request.setCharacterEncoding("UTF-8");
        // String resultadoJson = gson.toJson(actividadesList);
        response.setContentType("application/json");
        response.getWriter().write(respuesta.toString());    
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Lee el JSON del cuerpo de la solicitud
        BufferedReader reader = request.getReader();
        StringBuilder jsonInput = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonInput.append(line);
        }

        request.setCharacterEncoding("UTF-8");

        JsonObject jsonObject = JsonParser.parseString(jsonInput.toString()).getAsJsonObject();

        DataUsuario usuario = (DataUsuario) session.getAttribute("dataUsuario");
        String proveedor            = usuario.getNickname();
        String ciudad               = jsonObject.get("ciudad").getAsString();
        String url               	= jsonObject.get("urlVid").getAsString();
        String departamento         = jsonObject.get("departamento").getAsString();
        int duracion                = jsonObject.get("duracion").getAsInt();
        String nombre               = jsonObject.get("nombre").getAsString();
        String desc                 = jsonObject.get("desc").getAsString();
        int precio                  = jsonObject.get("precio").getAsInt();
        JsonArray categoriasArray   = jsonObject.getAsJsonArray("categorias");

        List<String> categorias = new ArrayList<>();
            
        for (JsonElement categoriaElement : categoriasArray) {
            // String categoria = categoriaElement.getAsString();
            String categoria = categoriaElement.getAsString();
            categorias.add(categoria);
        }

        System.out.println(ciudad);
        System.out.println(url);
        System.out.println(departamento);
        System.out.println(duracion);
        System.out.println(nombre);
        System.out.println(desc);
        System.out.println(precio);
        for (String categoria : categorias) {
            System.out.println(categoria);
        }

        try {
            Publicador API = new PublicadorService().getPublicadorPort();
            System.out.println("Valor de url antes de la evaluación del if: " + url);

            if (url == null || url.trim().isEmpty()) {
                System.out.println("Entre a sin URL");
                API.actividadCrearActividad(departamento, proveedor, nombre, desc, duracion, precio, ciudad, LocalDate.now().toString(), categorias);
            } else {
                System.out.println("Entre a con URL");
                API.actividadCrearActividadUrl(departamento, proveedor, nombre, desc, duracion, precio, ciudad, url, LocalDate.now().toString(), categorias);
            }
        } catch (Exception e) {
            System.out.println("Error crearActividad");
            e.printStackTrace();
            response.sendError(500, "Error al crear actividad.");
        }

        // System.out.println("[CrearActividadServlet] Hola");
        // String actividad = request.getParameter("actividad");
        
    }
}
