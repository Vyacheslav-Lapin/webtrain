package lab.ioc;

import lab.config.JavaConfig;
import lab.model.Person;
import lab.model.CountryImpl;
import lab.model.PersonImpl;
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
        return PersonImpl.builder()
                .id(1L)
                .age(35)
                .height(1.78F)
                .isProgrammer(true)
                .firstName("John")
                .lastName("Smith")
                .country(CountryImpl.builder().id(1).name("Russia").codeName("RU").build())
//                .contact(ContactImpl.builder().id(1).value("asd@asd.ru").build())
//                .contact(ContactImpl.builder().id(1).type("TELEPHONE").value("+55 11 99999-5555").build())
                .build();
    }
}
