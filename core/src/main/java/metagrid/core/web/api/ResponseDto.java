package metagrid.core.web.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<D> {
    D data;
}
