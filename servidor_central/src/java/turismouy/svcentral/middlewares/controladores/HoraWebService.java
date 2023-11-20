package turismouy.svcentral.middlewares.controladores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import java.text.SimpleDateFormat;
import java.util.Date;
import turismouy.svcentral.middlewares.interfaces.IHoraWebService;

// @WebService(endpointInterface = "turismouy.svcentral.HoraWebService")
@WebService
public class HoraWebService implements IHoraWebService {

    private Endpoint endpoint = null;

    public HoraWebService() {};

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:5000/API/HoraWebService", this);
        System.out.println("Publicando en la direccion: " + "http://localhost:5000/API/HoraWebService");
    };

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return this.endpoint;
    };

    @Override
    public String obtenerHoraActual() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Hora actual: " + formatoFecha.format(new Date());
    };
}