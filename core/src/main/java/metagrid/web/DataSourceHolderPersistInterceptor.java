package metagrid.web;

import lombok.extern.slf4j.Slf4j;
import metagrid.datasource.DataSourceHolder;
import metagrid.datasource.DataSourceRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@Slf4j
@Aspect
@Component
public class DataSourceHolderPersistInterceptor {
    
    private HttpServletRequest request;
    private DataSourceRepository repository;
    private DataSourceHolder holder;

    public DataSourceHolderPersistInterceptor(HttpServletRequest request, DataSourceRepository repository, DataSourceHolder holder) {
        this.request = request;
        this.repository = repository;
        this.holder = holder;
    }

    @Before("@annotation(withDataSource)")
    public void persist(JoinPoint jp, WithDataSource withDataSource) throws Throwable {
        String dataSourceName = this.request.getParameter("dataSourceName");
        if (StringUtils.isEmpty(dataSourceName)) {
            throw new BadRequestException("A dataSourceName is empty.");
        }
        
        DataSource dataSource = this.repository.get(dataSourceName)
                                    .orElseThrow(() -> new BadRequestException("A data source '" + dataSourceName + "' is not exists."));
        this.holder.set(dataSource);
        log.debug("Persist data source '{}'.", dataSourceName);
    }
}
