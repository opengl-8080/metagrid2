package metagrid.core.domain.definition;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.StringUtils;

@EqualsAndHashCode
@ToString
public class TableName {
    private final String value;
    
    public static TableName of(String value) {
        return new TableName(value);
    }

    private TableName(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("table name is required.");
        }
        this.value = value;
    }
    
    String asString() {
        return this.value;
    }
}
