package metagrid.web.api.tables;

import metagrid.web.api.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TablesController {
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto<List<TableDto>> get() {
        List<TableDto> tableDtoList = Arrays.asList(
            TableDto.builder().name("FOO_TABLE").logicalName("ふーてーぶる").build(),
            TableDto.builder().name("BAR_TABLE").logicalName("ばーてーぶる").build()
        );

        return ResponseDto.<List<TableDto>>builder().data(tableDtoList).build();
    }
}
