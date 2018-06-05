package lab.ioc;

import lab.JavaConfig;
import lab.model.Person;
import lab.model.SimpleContact;
import lab.model.SimpleCountry;
import lab.model.SimplePerson;
import lombok.experimental.FieldDefaults;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@FieldDefaults(level = PRIVATE, makeFinal = true)
class SimplePersonTest {

    static BeanFactory context =
        new AnnotationConfigApplicationContext(JavaConfig.class);

    @Test
    @DisplayName("Spring is alive!")
    void testInitPerson() {
        assertThat(context.getBean("person"), Is.is(getExpectedPerson()));
    }

    private Person getExpectedPerson() {
        return SimplePerson.builder()
                .id(1)
                .firstName("John")
                .lastName("Smith")
                .age(35)
                .isProgrammer(true)
                .height(1.78f)
                .broke(false)
                .country(new SimpleCountry(1L, "Russia", "RU"))
                .contact(new SimpleContact(1L, "EMAIL", "asd@asd.ru"))
                .contact(new SimpleContact(1L, "TELEPHONE", "+55 11 99999-5555"))
                .build();
    }
}