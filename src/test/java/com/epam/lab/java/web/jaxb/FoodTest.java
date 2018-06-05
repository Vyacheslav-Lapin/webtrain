package com.epam.lab.java.web.jaxb;

import io.vavr.CheckedFunction1;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

@FieldDefaults(level = PRIVATE, makeFinal = true)
class FoodTest {

    static String XML = "bean.xml";
    static File FILE = new File(XML);
    static Food BEAN = new Food(123, "nnn", "234.25", "ddd", 333);
    static JAXBContext CONTEXT = CheckedFunction1.<Class, JAXBContext>of(JAXBContext::newInstance)
            .unchecked()
            .apply(Food.class);

    @BeforeEach
    void setUp() {
        if (FILE.exists())
            //noinspection ResultOfMethodCallIgnored
            FILE.delete();
    }

    @Test
    @SneakyThrows
    @DisplayName("-parameters compiler flag is set")
    void parametersCompilerFlagIsSet() {
        Constructor<Food> foodConstructor =
                Food.class.getConstructor(
                        int.class,
                        String.class,
                        String.class,
                        String.class,
                        int.class);

        Arrays.stream(foodConstructor.getParameters())
                .map(Parameter::getName)
                .forEach(name -> assertFalse(name.startsWith("arg")));
    }

    @Test
    @SneakyThrows
    @DisplayName("Marshalling and unmarshalling works correctly")
    void marshallingAndUnmarshallingWorksCorrectly() {
        CONTEXT.createMarshaller().marshal(BEAN, FILE);
        val extractedBean = unmarshal(FILE);

        assertThat(extractedBean, is(BEAN));
    }

    @SneakyThrows
    private <T> T unmarshal(File file) {
        //noinspection unchecked
        return (T) CONTEXT.createUnmarshaller().unmarshal(file);
    }
}