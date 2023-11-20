package turismouy.svcentral.middlewares.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IHoraWebService {

    @WebMethod
    String obtenerHoraActual();
}
