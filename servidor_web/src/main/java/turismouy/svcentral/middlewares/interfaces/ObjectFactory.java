
package turismouy.svcentral.middlewares.interfaces;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the turismouy.svcentral.middlewares.interfaces package. 
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

    private final static QName _Saludo_QNAME = new QName("http://interfaces.middlewares.svcentral.turismouy/", "saludo");
    private final static QName _SaludoGeneral_QNAME = new QName("http://interfaces.middlewares.svcentral.turismouy/", "saludoGeneral");
    private final static QName _SaludoGeneralResponse_QNAME = new QName("http://interfaces.middlewares.svcentral.turismouy/", "saludoGeneralResponse");
    private final static QName _SaludoResponse_QNAME = new QName("http://interfaces.middlewares.svcentral.turismouy/", "saludoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: turismouy.svcentral.middlewares.interfaces
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Saludo }
     * 
     */
    public Saludo createSaludo() {
        return new Saludo();
    }

    /**
     * Create an instance of {@link SaludoGeneral }
     * 
     */
    public SaludoGeneral createSaludoGeneral() {
        return new SaludoGeneral();
    }

    /**
     * Create an instance of {@link SaludoGeneralResponse }
     * 
     */
    public SaludoGeneralResponse createSaludoGeneralResponse() {
        return new SaludoGeneralResponse();
    }

    /**
     * Create an instance of {@link SaludoResponse }
     * 
     */
    public SaludoResponse createSaludoResponse() {
        return new SaludoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Saludo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Saludo }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.middlewares.svcentral.turismouy/", name = "saludo")
    public JAXBElement<Saludo> createSaludo(Saludo value) {
        return new JAXBElement<Saludo>(_Saludo_QNAME, Saludo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaludoGeneral }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaludoGeneral }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.middlewares.svcentral.turismouy/", name = "saludoGeneral")
    public JAXBElement<SaludoGeneral> createSaludoGeneral(SaludoGeneral value) {
        return new JAXBElement<SaludoGeneral>(_SaludoGeneral_QNAME, SaludoGeneral.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaludoGeneralResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaludoGeneralResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.middlewares.svcentral.turismouy/", name = "saludoGeneralResponse")
    public JAXBElement<SaludoGeneralResponse> createSaludoGeneralResponse(SaludoGeneralResponse value) {
        return new JAXBElement<SaludoGeneralResponse>(_SaludoGeneralResponse_QNAME, SaludoGeneralResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaludoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaludoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.middlewares.svcentral.turismouy/", name = "saludoResponse")
    public JAXBElement<SaludoResponse> createSaludoResponse(SaludoResponse value) {
        return new JAXBElement<SaludoResponse>(_SaludoResponse_QNAME, SaludoResponse.class, null, value);
    }

}
