package ldars;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class CustomerAPITest {
	private static Logger logger = Logger.getLogger(CustomerAPITest.class);
	
	public void testaddCustomer(){
		
	} 
	
	@Test
	public void testAddVerification(){
//		CustomerModel custoemrModel=new CustomerModel();
//		CustomerServiceImpl csi=new CustomerServiceImpl();
//		assertEquals("会员账号不能为空;会员类型不能为空;会员状态不能为空;",csi.addVerification(custoemrModel));
//		custoemrModel.setCmMemid("sad");
//		assertEquals("会员类型不能为空;会员状态不能为空;",csi.addVerification(custoemrModel));
//		custoemrModel.setCtype("sad");
//		assertEquals("会员状态不能为空;",csi.addVerification(custoemrModel));
//		custoemrModel.setCstatus("sad");
//		assertEquals("",csi.addVerification(custoemrModel));
	}
}
