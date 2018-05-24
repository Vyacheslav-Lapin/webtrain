package com.epam.lab.java.web.jaxb;

import lombok.SneakyThrows;
import lombok.val;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class FoodTest {

    public static final String XML = "stud.xml";

    @Test
    @SneakyThrows
    @DisplayName("Name method works correctly")
    void testName() {
        JAXBContext context = JAXBContext.newInstance(Food.class);
        Marshaller marshaller = context.createMarshaller();

        Food food = new Food()
                .setId(123)
                .setName("nnn")
                .setDescription("ddd")
                .setCalories(234)
                .setPrice("333");

        File file = new File(XML);

        //put
        try (val fileOutputStream = new FileOutputStream(file)) {
            marshaller.marshal(food, fileOutputStream);
        }

        //get
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        val food2 = (Food) jaxbUnmarshaller.unmarshal(file);

        assertThat(food, is(food2));
    }
}