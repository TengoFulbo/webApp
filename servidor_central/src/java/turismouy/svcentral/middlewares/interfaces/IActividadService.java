package turismouy.svcentral.middlewares.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

@WebService
public interface IActividadService {
    @SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)

    @WebMethod
    void crearActividad(String nombreDepto, String nombreProv, String nombre, String desc, int duracion, int costoUni, String ciudad, List<String> categorias);
    
}
