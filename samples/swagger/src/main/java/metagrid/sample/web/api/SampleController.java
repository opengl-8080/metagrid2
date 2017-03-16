package metagrid.sample.web.api;

import metagrid.sample.service.SampleService;
import metagrid.core.web.WithDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleController {
    
    private SampleService service;

    public SampleController(SampleService service) {
        this.service = service;
    }

    @WithDataSource
    @GetMapping(params = "dataSourceName")
    public String hello() {
        this.service.test();
        return "hello";
    }
}
