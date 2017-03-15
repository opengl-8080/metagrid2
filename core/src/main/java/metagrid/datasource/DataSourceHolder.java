package metagrid.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.util.Objects;

@Slf4j
@Component
@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class DataSourceHolder {
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
    public void set(DataSource dataSource) {
        Objects.requireNonNull(dataSource, "A dataSource is required.");
        this.dataSource = dataSource;
    }

    DataSource get() {
        this.validate();
        return this.dataSource;
    }
    
    public JdbcTemplate getJdbcTemplate() {
        this.validate();
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        }
        
        return this.jdbcTemplate;
    }
    
    private void validate() {
        if (this.dataSource == null) {
            throw new IllegalStateException("A dataSource is not initialized.");
        }
    }
}
