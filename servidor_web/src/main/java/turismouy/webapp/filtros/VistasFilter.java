package turismouy.webapp.filtros;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import turismouy.svcentral.middlewares.DataActividad;
import turismouy.svcentral.middlewares.Publicador;
import turismouy.svcentral.middlewares.PublicadorService;
import turismouy.svcentral.utilidades.log;

@WebFilter("/consultaActividad")
public class VistasFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Obtén el nombre de la actividad desde el parámetro de la URL
        String nombreActividad = request.getParameter("nombreActividad");

        // Llamamos al web service.
        Publicador API = new PublicadorService().getPublicadorPort();

        DataActividad actividad = API.actividadGetActividad(nombreActividad);
        
        if (actividad != null) {
            try {
                API.actividadAumentarVisita(nombreActividad);
            } catch (Exception e) {
                // TODO: handle exception
            }
            log.info("La actividad '" + nombreActividad + "' obtuvo una visita");
        }
        
        // Continúa con la cadena de filtros
        chain.doFilter(request, response);
    }
}
