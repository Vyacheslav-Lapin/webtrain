package lab.ioc;

import lab.config.JavaConfig;
import lab.model.CountryImpl;
import lab.model.Person;
import lab.model.PersonImpl;
import lombok.experimental.FieldDefaults;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;

@FieldDefaults(level = PRIVATE, makeFinal = true)
class PersonImplTest {

    static BeanFactory context =
            new AnnotationConfigApplicationContext(JavaConfig.class);

    @Test
    @DisplayName("Spring is alive!")
    void testInitPerson() {
        assertThat(context.getBean("person"), Is.is(getExpectedPerson()));
    }

    private Person getExpectedPerson() {
        return PersonImpl.builder()
                .id(1)
                .firstName("John")
                .lastName("Smith")
                .age(35)
                .isProgrammer(true)
                .height(1.78f)
                .broke(false)
                .country(new CountryImpl(1L, "Russia", "RU"))
//                .contact(ContactImpl.builder().id(1L).value("asd@asd.ru").build())
//                .contact(ContactImpl.builder().id(1L).type("TELEPHONE").value("+55 11 99999-5555").build())
                .build();
    }
}