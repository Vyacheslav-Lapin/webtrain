package lab.ioc;

import lab.JavaConfig;
import lab.model.Person;
import lab.model.SimpleContact;
import lab.model.SimpleCountry;
import lab.model.SimplePerson;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = PRIVATE, makeFinal = true)
class SimpleAppTest {

    static BeanFactory BEAN_FACTORY = new AnnotationConfigApplicationContext(
            JavaConfig.class);

    @Test
    void testInitPerson() {
        assertEquals(getExpectedPerson(), BEAN_FACTORY.getBean("person"));
    }

    static Person getExpectedPerson() {
        return SimplePerson.builder()
                .id(1L)
                .age(35)
                .height(1.78F)
                .isProgrammer(true)
                .firstName("John")
                .lastName("Smith")
                .country(SimpleCountry.builder().id(1).name("Russia").codeName("RU").build())
                .contact(SimpleContact.builder().id(1).value("asd@asd.ru").build())
                .contact(SimpleContact.builder().id(1).type("TELEPHONE").value("+55 11 99999-5555").build())
                .build();
    }
}
