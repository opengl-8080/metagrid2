package metagrid.core.web.api.tables;

import metagrid.core.MetaGridCoreConfiguration;
import metagrid.core.MetagridTestConfiguration;
import metagrid.core.web.api.ResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = {MetaGridCoreConfiguration.class, MetagridTestConfiguration.class},
    loader = SpringBootContextLoader.class
)
@WebAppConfiguration
public class TablesControllerTest {
    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void name() throws Exception {
        ResponseDto<Object> expected = ResponseDto.builder()
                .data(Arrays.asList(
                    TableDto.builder().name("TABLE_1").build(),
                    TableDto.builder().name("TABLE_2").build(),
                    TableDto.builder().name("TABLE_3").build()
                )).build();

        this.mockMvc.perform(get("/api/tables")
                .param("dataSourceName", "test"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("responseDto", expected));
    }
}