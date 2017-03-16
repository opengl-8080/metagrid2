package metagrid.sample.service;

import metagrid.core.datasource.DataSourceHolder;
import metagrid.core.datasource.WithTransaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@WithTransaction
public class SampleService {
    
    private DataSourceHolder holder;

    public SampleService(DataSourceHolder holder) {
        this.holder = holder;
    }

    public void test() {
        JdbcTemplate jdbcTemplate = this.holder.getJdbcTemplate();
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from test_table");
        result.forEach(System.out::println);
    }
}
