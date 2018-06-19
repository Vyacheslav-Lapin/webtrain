package lab;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lab.dao.jdbc.JdbcCountryDao;
import lab.model.Contact;
import lab.model.SimpleContact;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.val;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.*;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.transaction.TransactionManager;
import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ImportResource("tx.xml")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ComponentScan({"lab.model", "lab.aop", "lab.dao.jdbc", "lab.dao.jpa", "lab.service"})
public class JavaConfig {

    InstrumentationLoadTimeWeaver instrumentationLoadTimeWeaver;

    static String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    @Bean
    public List<Contact> contacts() {
        return Arrays.asList(
                SimpleContact.builder().id(1).value("asd@asd.ru").build(),
                SimpleContact.builder().id(1).type("TELEPHONE").value("+55 11 99999-5555").build()
        );
    }

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
//                .setType(H2)
//                .setScriptEncoding("UTF-8")
//                .ignoreFailedDrops(true)
//                .addScript("db-schema.sql")
//                .build();
//    }

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource dataSource() {
        val ds = new ComboPooledDataSource();
        ds.setJdbcUrl(DB_URL);
//        ds.setUser("dbuser");
//        ds.setPassword("dbpassword");

        // Optional Settings
        ds.setInitialPoolSize(5);
        ds.setMinPoolSize(5);
        ds.setAcquireIncrement(5);
        ds.setMaxPoolSize(20);
        ds.setMaxStatements(100);

        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        val bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        bean.setPersistenceUnitName("springframework.lab.orm.jpa");
        bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        bean.setLoadTimeWeaver(instrumentationLoadTimeWeaver);
        return bean;
    }

    @Bean
    public JdbcCountryDao countryDao() {
        return new JdbcCountryDao(dataSource());
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
