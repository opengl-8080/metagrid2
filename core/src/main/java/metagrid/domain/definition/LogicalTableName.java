package metagrid.domain.definition;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.StringUtils;

@ToString
@EqualsAndHashCode
public class LogicalTableName {
    private final String value;
    
    public static LogicalTableName of(String value) {
        return new LogicalTableName(value);
    }

    private LogicalTableName(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("logical table name is required.");
        }
        this.value = value;
    }
    
    String asString() {
        return this.value;
    }
}
