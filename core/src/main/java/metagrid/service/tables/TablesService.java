package metagrid.service.tables;

import metagrid.datasource.DataSourceHolder;
import metagrid.datasource.WithTransaction;
import metagrid.domain.definition.TableDefinition;
import metagrid.domain.definition.TableName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.*;

@Component
@WithTransaction
public class TablesService {
    
    private DataSourceHolder holder;

    public TablesService(DataSourceHolder holder) {
        this.holder = holder;
    }
    
    public List<TableDefinition> searchTableDefinitionList() {
        JdbcTemplate jdbc = this.holder.getJdbcTemplate();
        List<String> tableNames = jdbc.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES ORDER BY TABLE_NAME ASC", String.class);
        
        return tableNames.stream()
                .map(TableName::of)
                .map(TableDefinition::new)
                .collect(toList());
    }
}
