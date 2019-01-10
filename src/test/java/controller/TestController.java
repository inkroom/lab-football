package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)     //调用Spring单元测试类
@WebAppConfiguration(value = "web")  //调用Java Web组件，如自动注入ServletContext Bean等
@ContextConfiguration(locations = {"classpath*:spring/spring-*.xml","classpath:spring/springMVC-servlet.xml"})      //加载Spring配置文件
public class TestController {
    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;        //SpringMVC提供的Controller测试类


    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wac).build();
    }

    @Test
    public void testGetUser() throws Exception {
        url = "/referee/consummateIndex";


        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(url)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("name", "3").param("id","510").param("code","code"))
                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("name"))
                .andReturn();
//        assertEquals(m.toString(), result.getModelAndView().getModel().get("user").toString());
        System.out.println(result);
    }

    private String url;


}
