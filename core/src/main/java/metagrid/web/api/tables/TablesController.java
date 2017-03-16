package metagrid.web.api.tables;

import metagrid.domain.definition.TableDefinition;
import metagrid.service.tables.TablesService;
import metagrid.util.Lists;
import metagrid.web.WithDataSource;
import metagrid.web.api.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TablesController {
    
    private TablesService service;

    public TablesController(TablesService service) {
        this.service = service;
    }

    @WithDataSource
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto<List<TableDto>> get() {
        List<TableDefinition> tableDefinitionList = this.service.searchTableDefinitionList();
        List<TableDto> tableDtoList = Lists.to(tableDefinitionList, TableDto::map);

        return ResponseDto.<List<TableDto>>builder().data(tableDtoList).build();
    }
}
