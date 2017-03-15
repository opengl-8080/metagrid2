package metagrid.web.api.sample;

import metagrid.service.sample.SampleService;
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

    @GetMapping(headers = "DataSourceName=sample")
    public String hello() {
        this.service.test();
        return "hello";
    }
}
