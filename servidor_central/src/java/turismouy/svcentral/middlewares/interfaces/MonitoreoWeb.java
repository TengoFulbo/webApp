package turismouy.svcentral.middlewares.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import turismouy.svcentral.middlewares.controladores.Monitoreo;

// @WebService(endpointInterface = "turismouy.svcentral.middlewares.controladores.Monitoreo")
@WebService
public class MonitoreoWeb implements Monitoreo {

    private Endpoint endpoint = null;

    public MonitoreoWeb() {};

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:5000/API/Monitoreo", this);
    };

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    };

    @Override
    public String saludo(String nombre) {
        return "Hola " + nombre;
    };

    @Override
    public String saludoGeneral() {
        return "Hola a todos!";
    };
}
