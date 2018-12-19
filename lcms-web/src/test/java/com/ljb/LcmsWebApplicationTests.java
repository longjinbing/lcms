package com.ljb;

import com.ljb.service.DeptService;
import com.ljb.service.MenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.ljb")
public class LcmsWebApplicationTests {

    private static Log logger= LogFactory.getLog(LcmsWebApplicationTests.class);

    @Autowired
    private MenuService menuService;
    @Autowired
    private DeptService deptService;

    @Test
    public void contextLoads() {
    }

    @Test
    public  void buildTree(){
    }


}
