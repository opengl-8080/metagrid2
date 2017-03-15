package metagrid.web.api.tables;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableDto {
    String name;
    String logicalName;
}
