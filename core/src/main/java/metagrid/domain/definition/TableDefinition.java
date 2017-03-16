package metagrid.domain.definition;

import lombok.ToString;
import metagrid.domain.definition.bean.TableDefinitionBean;

import java.util.Objects;

@ToString
public class TableDefinition {
    private TableName tableName;
    private LogicalTableName logicalTableName;

    public TableDefinition(TableName tableName) {
        Objects.requireNonNull(tableName, "table tableName is required.");
        this.tableName = tableName;
    }
    
    public void setLogicalTableName(LogicalTableName logicalTableName) {
        this.logicalTableName = logicalTableName;
    }

    public TableDefinitionBean toBean() {
        return TableDefinitionBean.builder()
                .tableName(this.tableName.asString())
                .logicalTableName(this.logicalTableName != null ? this.logicalTableName.asString() : null)
                .build();
    }
}