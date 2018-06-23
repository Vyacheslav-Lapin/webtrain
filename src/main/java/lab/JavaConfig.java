package lab;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.*;

@Configuration
@AllArgsConstructor
//@EnableAspectJAutoProxy
@ImportResource("classpath*:lab-9-mvc-servlet.xml")
//@FieldDefaults(level = PRIVATE, makeFinal = true)
//@ComponentScan({"lab.model", "lab.aop", "lab.dao.jdbc",
//        "lab.dao.jpa", "lab.service", "lab.mvc"})
public class JavaConfig {

//    static String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
//    InstrumentationLoadTimeWeaver instrumentationLoadTimeWeaver;

//    @Bean
//    public List<Contact> contacts() {
//        return Arrays.asList(
//                ContactImpl.builder().id(1).value("asd@asd.ru").build(),
//                ContactImpl.builder().id(1).type("TELEPHONE").value("+55 11 99999-5555").build()
//        );
//    }

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
