package com.epam.lab.java.web.wsdl;

import lombok.experimental.FieldDefaults;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import static javax.jws.soap.SOAPBinding.Style.RPC;
import static lombok.AccessLevel.PRIVATE;

@WebService
@SOAPBinding(style= RPC)
@FieldDefaults(level = PRIVATE)
public class Hello {
    public String sayHello(@WebParam(name = "name") String name) {
        return "Hello, " + name;
    }
}
