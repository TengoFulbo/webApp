package turismouy.svcentral.middlewares.controladores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

@WebService
public interface Monitoreo {
    @SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)

    @WebMethod
    String saludo(String nombre);

    @WebMethod
    String saludoGeneral();
}
