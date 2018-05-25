package com.epam.lab.java.web.webapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.CheckedFunction1;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.function.Function;

interface JsonRestfulWebResource {

    Function<Object, String> toJsonExceptional =
            CheckedFunction1.of(new ObjectMapper().writer().withDefaultPrettyPrinter()::writeValueAsString)
            .unchecked();

    default String toJson(Object o) {
        return toJsonExceptional.apply(o);
    }

    default Response ok(Collection<?> objects) {
        return Response.ok(toJson(objects)).build();
    }

    default Response ok(Object o) {
        return Response.ok(toJson(o)).build();
    }

    default Response noContent() {
        return Response.noContent().build();
    }
}