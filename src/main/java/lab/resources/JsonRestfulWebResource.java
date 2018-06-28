package lab.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.CheckedFunction1;
import lombok.SneakyThrows;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.function.Function;

public interface JsonRestfulWebResource {

    ObjectMapper objectMapper = new ObjectMapper();

    Function<Object, String> toJsonExceptional =
            CheckedFunction1.of(
                    objectMapper.writer().withDefaultPrettyPrinter()::writeValueAsString)
                    .unchecked();

    @SneakyThrows
    default <T> T fromJSON(String s, Class<T> aClass) {
        return objectMapper.readValue(s, aClass);
    }

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
