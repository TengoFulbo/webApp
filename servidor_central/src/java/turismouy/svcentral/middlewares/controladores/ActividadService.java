package turismouy.svcentral.middlewares.controladores;

import java.time.LocalDate;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import turismouy.svcentral.middlewares.interfaces.IActividadService;
import turismouy.svcentral.utilidades.log;

// TODO: Faltan excepciones.
@WebService
public class ActividadService implements IActividadService {
    private Endpoint endpoint = null;
    private String URI = "http://localhost:5000/API/ActividadService";

    public ActividadService() {};

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish(URI, this);
        log.info("Publicando en la direccion: " + URI);
    };

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return this.endpoint;
    };

    @Override
    public void crearActividad(String nombreDepto, String nombreProv, String nombre, String desc, int duracion, int costoUni, String ciudad, List<String> categorias) {
        LocalDate.now();
        log.info("nombreDepto: "    + nombreDepto);
        log.info("nombreProv: "     + nombreProv);
        log.info("nombre: "         + nombre);
        log.info("desc: "           + desc);
        log.info("duracion: "       + duracion);
        log.info("costoUni: "       + costoUni);
        log.info("ciudad: "         + ciudad);
        log.info("Hola, tamo generado una actividad usando un Web Service");
    }
}
