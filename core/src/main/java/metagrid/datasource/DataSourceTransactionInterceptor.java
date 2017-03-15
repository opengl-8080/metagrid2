package metagrid.datasource;

import lombok.extern.slf4j.Slf4j;
import metagrid.MetaGridException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Aspect
@Component
public class DataSourceTransactionInterceptor {
    
    private DataSourceHolder dataSourceHolder;

    public DataSourceTransactionInterceptor(DataSourceHolder dataSourceHolder) {
        this.dataSourceHolder = dataSourceHolder;
    }

    @Around("@within(service)")
    public Object aroundTransaction(ProceedingJoinPoint jp, Service service) throws Throwable {
        try {
            log.debug("TransactionInterceptor intercepts method invocation.");
            this.dataSourceHolder.beginTransaction();
            Object result = jp.proceed();
            this.dataSourceHolder.commit();
            return result;
        } catch (Exception e) {
            throw new MetaGridException(e);
        } finally {
            this.dataSourceHolder.rollback();
            log.debug("TransactionInterceptor finishes the intercepting.");
        }
    }
}
