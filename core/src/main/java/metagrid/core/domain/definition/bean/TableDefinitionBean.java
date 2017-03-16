package metagrid.core.domain.definition.bean;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TableDefinitionBean {
    private final String tableName;
    private final String logicalTableName;
}
