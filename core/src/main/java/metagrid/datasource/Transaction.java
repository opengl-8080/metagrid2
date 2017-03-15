package metagrid.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@Slf4j
class Transaction {
    
    private PlatformTransactionManager manager;
    private TransactionStatus status;
    
    Transaction(DataSource dataSource) {
        this.manager = new DataSourceTransactionManager(dataSource);
    }
    
    void begin() {
        TransactionDefinition def = new DefaultTransactionDefinition();
        this.status = this.manager.getTransaction(def);
        log.debug("Begin transaction.");
    }
    
    void commit() {
        if (this.status.isCompleted()) {
            log.debug("Skip commit transaction.");
            return;
        }
        this.manager.commit(this.status);
        log.debug("Commit transaction.");
    }
    
    void rollback() {
        if (this.status.isCompleted()) {
            log.debug("Skip rollback transaction.");
            return;
        }
        this.manager.rollback(this.status);
        log.debug("Rollback transaction.");
    }
}
