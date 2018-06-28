package lab.config;

import lab.model.Contact;
import lab.model.ContactImpl;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.*;

import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@ComponentScan
@AllArgsConstructor
@EnableAspectJAutoProxy
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ImportResource("classpath*:mvc.xml")
public class JavaConfig {

//    static String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
//    InstrumentationLoadTimeWeaver instrumentationLoadTimeWeaver;

    @Bean
    public List<Contact> contacts() {
        return Arrays.asList(
                ContactImpl.builder().id(1).value("asd@asd.ru").build(),
                ContactImpl.builder().id(1).type("TELEPHONE").value("+55 11 99999-5555").build()
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

//    @Bean(destroyMethod = "close")
//    public ComboPooledDataSource dataSource() {
//        val ds = new ComboPooledDataSource();
//        ds.setJdbcUrl(DB_URL);
////        ds.setUser("dbuser");
////        ds.setPassword("dbpassword");
//
//        // Optional Settings
//        ds.setInitialPoolSize(5);
//        ds.setMinPoolSize(5);
//        ds.setAcquireIncrement(5);
//        ds.setMaxPoolSize(20);
//        ds.setMaxStatements(100);
//
//        return ds;
//    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
//        val bean = new LocalContainerEntityManagerFactoryBean();
//        bean.setDataSource(dataSource());
//        bean.setPersistenceUnitName("springframework.lab.orm.jpa");
//        bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        bean.setLoadTimeWeaver(instrumentationLoadTimeWeaver);
//        return bean;
//    }

//    @Bean
//    public JdbcCountryDao countryDao() {
//        return new JdbcCountryDao(dataSource());
//    }

//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
}
