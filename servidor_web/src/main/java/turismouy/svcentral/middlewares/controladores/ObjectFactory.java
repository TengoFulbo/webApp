
package turismouy.svcentral.middlewares.controladores;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the turismouy.svcentral.middlewares.controladores package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ObtenerHoraActual_QNAME = new QName("http://controladores.middlewares.svcentral.turismouy/", "obtenerHoraActual");
    private final static QName _ObtenerHoraActualResponse_QNAME = new QName("http://controladores.middlewares.svcentral.turismouy/", "obtenerHoraActualResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: turismouy.svcentral.middlewares.controladores
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObtenerHoraActual }
     * 
     */
    public ObtenerHoraActual createObtenerHoraActual() {
        return new ObtenerHoraActual();
    }

    /**
     * Create an instance of {@link ObtenerHoraActualResponse }
     * 
     */
    public ObtenerHoraActualResponse createObtenerHoraActualResponse() {
        return new ObtenerHoraActualResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerHoraActual }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerHoraActual }{@code >}
     */
    @XmlElementDecl(namespace = "http://controladores.middlewares.svcentral.turismouy/", name = "obtenerHoraActual")
    public JAXBElement<ObtenerHoraActual> createObtenerHoraActual(ObtenerHoraActual value) {
        return new JAXBElement<ObtenerHoraActual>(_ObtenerHoraActual_QNAME, ObtenerHoraActual.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerHoraActualResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerHoraActualResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://controladores.middlewares.svcentral.turismouy/", name = "obtenerHoraActualResponse")
    public JAXBElement<ObtenerHoraActualResponse> createObtenerHoraActualResponse(ObtenerHoraActualResponse value) {
        return new JAXBElement<ObtenerHoraActualResponse>(_ObtenerHoraActualResponse_QNAME, ObtenerHoraActualResponse.class, null, value);
    }

}
