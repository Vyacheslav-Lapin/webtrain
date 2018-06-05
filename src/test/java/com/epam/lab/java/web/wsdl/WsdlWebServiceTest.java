package com.epam.lab.java.web.wsdl;

import com.epam.lab.java.web.wsdl.wsclient.Hello;
import com.epam.lab.java.web.wsdl.wsclient.HelloService;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;

import javax.xml.ws.Endpoint;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@FieldDefaults(level = PRIVATE)
class WsdlWebServiceTest {

    static final String HENRY = "Henry";
    Endpoint endpoint;
    Hello hello;

    @BeforeEach
    void setUp() {
        endpoint = Service.publish();
        hello = new HelloService().getHelloPort();
    }

    @Test
    @Tag("slow")
    @DisplayName("Hello web service works correctly")
    void testWebService() {
        assertThat(hello.sayHello(HENRY), is("Hello, " + HENRY));
    }

    @AfterEach
    void tearDown() {
        endpoint.stop();
    }
}