package lab;

import lab.model.Contact;
import lab.model.SimpleContact;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@ImportResource("aop.xml")
@FieldDefaults(level = PRIVATE)
@ComponentScan({"lab.model", "lab.aop"})
public class JavaConfig {

    @Bean
    public List<Contact> contacts() {
        return Arrays.asList(
                SimpleContact.builder().id(1).value("asd@asd.ru").build(),
                SimpleContact.builder().id(1).type("TELEPHONE").value("+55 11 99999-5555").build()
        );
    }
}
