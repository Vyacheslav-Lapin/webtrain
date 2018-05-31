package com.epam.lab.java.web.model;

import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = PRIVATE, makeFinal = true)
class SimplePersonTest {

    static String APPLICATION_CONTEXT_XML_FILE_NAME = "ioc.xml";

    BeanFactory context = new ClassPathXmlApplicationContext(
            APPLICATION_CONTEXT_XML_FILE_NAME);

    @Test
    @DisplayName("Spring is alive!")
    void testInitPerson() {
        assertEquals(getExpectedPerson(), context.getBean("person"));
    }

    private Person getExpectedPerson() {
        return SimplePerson.builder()
                .firstName("John")
                .lastName("Smith")
                .age(35)
                .isProgrammer(true)
                .height(1.78f)
                .broke(false)
                .country(new SimpleCountry("Russia", "RU"))
                .contact(new SimpleContact("EMAIL", "asd@asd.ru"))
                .contact(new SimpleContact("TELEPHONE", "+55 11 99999-5555"))
                .build();
    }
}