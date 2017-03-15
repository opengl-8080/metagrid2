package metagrid.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceTransactionInterceptor {
    
    private DataSourceHolder dataSourceHolder;

    public DataSourceTransactionInterceptor(DataSourceHolder dataSourceHolder) {
        this.dataSourceHolder = dataSourceHolder;
    }

    @Around("@within(withTransaction)")
    public Object transaction(ProceedingJoinPoint jp, WithTransaction withTransaction) throws Throwable {
        Transaction tx = new Transaction(this.dataSourceHolder.get());
        try {
            tx.begin();
            Object result = jp.proceed();
            tx.commit();
            return result;
        } finally {
            tx.rollback();
        }
    }
}
