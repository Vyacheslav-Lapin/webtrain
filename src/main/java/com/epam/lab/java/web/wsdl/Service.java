package com.epam.lab.java.web.wsdl;

import lombok.experimental.FieldDefaults;

import javax.xml.ws.Endpoint;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class Service {

    static String url = "http://localhost:1212/hello";

    public static Endpoint publish() {
        return Endpoint.publish(url, new Hello());
    }

    public static void main(String... args) {
        publish();
        System.out.println("Service started @ " + url + "?wsdl");
    }
}
