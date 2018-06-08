package lab;

import lab.model.Contact;
import lab.model.SimpleContact;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@EnableAspectJAutoProxy
@ImportResource("orm.xml")
@FieldDefaults(level = PRIVATE)
@ComponentScan({"lab.model", "lab.aop", "lab.dao"})
public class JavaConfig {

    @Bean
    public List<Contact> contacts() {
        return Arrays.asList(
                SimpleContact.builder().id(1).value("asd@asd.ru").build(),
                SimpleContact.builder().id(1).type("TELEPHONE").value("+55 11 99999-5555").build()
        );
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("db-schema.sql")
                .build();
    }
}
