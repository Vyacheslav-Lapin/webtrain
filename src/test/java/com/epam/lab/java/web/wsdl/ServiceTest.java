package com.epam.lab.java.web.wsdl;

import com.epam.lab.java.web.wsdl.wsclient.Hello;
import com.epam.lab.java.web.wsdl.wsclient.HelloService;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.ws.Endpoint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ServiceTest {

    static final String HENRY = "Henry";
    Endpoint publish;

    @BeforeEach
    void setUp() {
        publish = Service.publish();
    }

    @Test
    @DisplayName("Main method works correctly")
    void testMain() {
        val service = new HelloService();
        Hello hello = service.getHelloPort();

        assertThat(hello.sayHello(HENRY), is("Hello, " + HENRY));
    }

    @AfterEach
    void tearDown() {
        publish.stop();
    }
}