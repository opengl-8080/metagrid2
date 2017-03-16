package metagrid.core.web.api.tables;

import metagrid.core.domain.definition.TableDefinition;
import metagrid.core.service.tables.TablesService;
import metagrid.core.util.Lists;
import metagrid.core.web.WithDataSource;
import metagrid.core.web.api.ResponseDto;
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
