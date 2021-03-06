package metagrid.core.datasource;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DataSourceRepository {
    private Map<String, DataSource> dataSourceMap = new HashMap<>();
    
    public synchronized void registerNewDataSource(String name, DataSource dataSource) {
        if (this.dataSourceMap.containsKey(name)) {
            throw new IllegalArgumentException("A data source '" + name + "' already exists.");
        }
        this.dataSourceMap.put(name, dataSource);
    }
    
    public synchronized Optional<DataSource> get(String name) {
        if (!this.dataSourceMap.containsKey(name)) {
            return Optional.empty();
        }

        return Optional.of(this.dataSourceMap.get(name));
    }
}
