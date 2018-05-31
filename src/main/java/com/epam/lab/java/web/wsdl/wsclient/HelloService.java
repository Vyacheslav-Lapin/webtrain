package com.epam.lab.java.web.wsdl.wsclient;

import io.vavr.CheckedFunction1;
import io.vavr.control.Either;
import lombok.experimental.FieldDefaults;
import lombok.val;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.URL;

import static lombok.AccessLevel.PRIVATE;

@WebServiceClient(name = "HelloService",
        targetNamespace = "http://wsdl.web.java.lab.epam.com/",
        wsdlLocation = "http://localhost:1212/hello?wsdl")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class HelloService extends Service {

    static Either<WebServiceException, URL> URL;

    @SuppressWarnings("SpellCheckingInspection")
    static QName SERVICE_QNAME;

    @SuppressWarnings("SpellCheckingInspection")
    static QName PORT_QNAME;

    static {
        val webServiceClient = HelloService.class.getDeclaredAnnotation(WebServiceClient.class);

        URL = CheckedFunction1.<String, URL>of(URL::new)
                .andThen(Either::<WebServiceException, URL>right)
                .recover(throwable -> __ -> Either.<Throwable, URL>left(throwable)
                        .mapLeft(WebServiceException::new))
                .apply(webServiceClient.wsdlLocation());

        String namespaceURI = webServiceClient.targetNamespace();
        String localPart = webServiceClient.name();
        SERVICE_QNAME = new QName(namespaceURI, localPart);
        PORT_QNAME = new QName(namespaceURI, localPart.replace("Service", "Port"));
    }

    public HelloService(WebServiceFeature... features) {
        super(getWsdlLocation(), SERVICE_QNAME, features);
    }

    private static URL getWsdlLocation() {
        return URL.getOrElseThrow(webServiceException -> webServiceException);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features
     *                 not in the <code>features</code> parameter will have their default values.
     * @return returns Hello
     */
    @WebEndpoint(name = "HelloPort")
    public Hello getHelloPort(WebServiceFeature... features) {
        return getPort(PORT_QNAME, Hello.class, features);
    }

}

