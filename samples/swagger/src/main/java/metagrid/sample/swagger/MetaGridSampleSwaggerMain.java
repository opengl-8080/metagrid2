package metagrid.sample.swagger;

import metagrid.MetaGridCoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@Import(MetaGridCoreConfiguration.class)
public class MetaGridSampleSwaggerMain {

    public static void main(String[] args) {
        SpringApplication.run(MetaGridSampleSwaggerMain.class, args);
    }
}
