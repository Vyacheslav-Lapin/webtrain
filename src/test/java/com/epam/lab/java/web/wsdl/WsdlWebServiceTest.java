package com.epam.lab.java.web.wsdl;

import com.epam.lab.java.web.wsdl.wsclient.Hello;
import com.epam.lab.java.web.wsdl.wsclient.HelloService;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.*;

import javax.xml.ws.Endpoint;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@FieldDefaults(level = PRIVATE, makeFinal = true)
class WsdlWebServiceTest {

    static String HENRY = "Henry";
    static Hello hello = new HelloService().getHelloPort();

    @NonFinal
    Endpoint publish;

    @BeforeEach
    void setUp() {
        publish = Service.publish();
    }

    @Test
    @Tag("slow")
    @DisplayName("Hello web service works correctly")
    void testWebService() {
        assertThat(hello.sayHello(HENRY), is("Hello, " + HENRY));
    }

    @AfterEach
    void tearDown() {
        publish.stop();
    }
}