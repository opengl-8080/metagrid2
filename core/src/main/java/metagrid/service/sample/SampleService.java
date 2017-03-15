package metagrid.service.sample;

import metagrid.datasource.DataSourceHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
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
