package metagrid.core;

import metagrid.core.datasource.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class MetagridTestConfiguration {
    
    @Autowired
    public void initDataSource(DataSourceRepository repository) {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.hsqldb.jdbcDriver")
                .url("jdbc:hsqldb:mem:test")
                .username("SA")
                .password("")
                .build();

        repository.registerNewDataSource("test", dataSource);
        
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            con.setAutoCommit(false);
            st.executeUpdate("CREATE TABLE TABLE_1 (ID INTEGER)");
            st.executeUpdate("CREATE TABLE TABLE_2 (ID INTEGER)");
            st.executeUpdate("CREATE TABLE TABLE_3 (ID INTEGER)");
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
