package metagrid.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@Slf4j
@Component
@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class DataSourceHolder {
    
    private final DataSourceRepository repository;
    private final HttpServletRequest request;
    private DataSource dataSource;
    private PlatformTransactionManager txManager;
    private TransactionStatus txStatus;
    private JdbcTemplate jdbcTemplate;

    public DataSourceHolder(HttpServletRequest request, DataSourceRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        String dataSourceName = this.request.getHeader("DataSourceName");
        
        if (StringUtils.isEmpty(dataSourceName)) {
            log.debug("A data source name is empty.");
            return;
        }
        
        this.dataSource = this.repository.get(dataSourceName);
        log.debug("DataSourceHolder prepares the data source '{}'.", dataSourceName);
    }
    
    public JdbcTemplate getJdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        }
        
        return this.jdbcTemplate;
    }
    
    void beginTransaction() {
        if (this.dataSource == null) {
            log.debug("DataSourceHolder skips the transaction to begin.");
            return;
        }
        
        this.txManager = new DataSourceTransactionManager(this.dataSource);
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        this.txStatus = this.txManager.getTransaction(txDef);
        log.debug("DataSourceHolder begins the transaction.");
    }
    
    void commit() {
        if (!this.enableTransaction()) {
            log.debug("DataSourceHolder skips the commit.");
            return;
        }
        
        this.txManager.commit(this.txStatus);
        log.debug("DataSourceHolder commits the transaction.");
    }
    
    void rollback() {
        if (!this.enableTransaction()) {
            log.debug("DataSourceHolder skips the rollback.");
            return;
        }

        this.txManager.rollback(this.txStatus);
        log.debug("DataSourceHolder rolls back the transaction.");
    }
    
    private boolean enableTransaction() {
        return this.dataSource != null
                && this.txStatus != null
                && !this.txStatus.isCompleted();
    }

    @PreDestroy
    public void destroy() {
        this.rollback();
        log.debug("Spring container destroys the instance of DataSourceHolder.");
    }
}
