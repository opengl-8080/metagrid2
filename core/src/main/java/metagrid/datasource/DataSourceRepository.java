package metagrid.datasource;

import metagrid.MetaGridException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataSourceRepository {
    private Map<String, DataSource> dataSourceMap = new HashMap<>();
    
    public synchronized void put(String name, DataSource dataSource) {
        this.dataSourceMap.put(name, dataSource);
    }
    
    synchronized DataSource get(String name) {
        if (!this.dataSourceMap.containsKey(name)) {
            throw new MetaGridException("DataSource '%s' is not found.", name);
        }

        return this.dataSourceMap.get(name);
    }
}
