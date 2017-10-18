package ldars;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ldars.model.UserModel;
import com.ldars.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)   
@ContextConfiguration(locations = {"classpath:/springconfig/spring-mybatis.xml","classpath:/springconfig/spring-crm.xml","classpath:spring-mvc.xml"})  
   
public class TestMyBatis {  
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
//    @Resource  
//    private RoleBuildingServiceImpl roleBuildingService ;  
//  
//    
//    @Test  
//    public void test2() { 
//       System.out.println(JSON.toJSONString( roleBuildingService.queryBuildingIdRoleId(3L)));  
//    }  
}  