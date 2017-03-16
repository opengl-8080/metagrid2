package metagrid.core.web.api.tables;

import lombok.Builder;
import lombok.Data;
import metagrid.core.domain.definition.TableDefinition;
import metagrid.core.domain.definition.bean.TableDefinitionBean;

@Data
@Builder
public class TableDto {
    String name;
    String logicalName;
    
    public static TableDto map(TableDefinition tableDefinition) {
        TableDefinitionBean bean = tableDefinition.toBean();
        
        return TableDto.builder()
                .name(bean.getTableName())
                .logicalName(bean.getLogicalTableName())
                .build();
    }
}
