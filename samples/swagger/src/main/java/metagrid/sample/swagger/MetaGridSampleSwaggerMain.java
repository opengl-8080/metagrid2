package metagrid.sample.swagger;

import metagrid.MetaGridCoreConfiguration;
import metagrid.datasource.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@EnableSwagger2
@SpringBootApplication
@Import(MetaGridCoreConfiguration.class)
public class MetaGridSampleSwaggerMain {

    public static void main(String[] args) {
        SpringApplication.run(MetaGridSampleSwaggerMain.class, args);
    }
    
    @Autowired
    public void initDataSource(DataSourceRepository repository) {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.hsqldb.jdbcDriver")
                .url("jdbc:hsqldb:mem:test")
                .username("SA")
                .password("")
                .build();
        
        repository.put("sample", dataSource);

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            con.setAutoCommit(false);
            st.executeUpdate("CREATE TABLE TEST_TABLE (ID INTEGER, VALUE VARCHAR(10))");
            st.executeUpdate("INSERT INTO TEST_TABLE VALUES (1, 'HOGE')");
            st.executeUpdate("INSERT INTO TEST_TABLE VALUES (2, 'FUGA')");
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
