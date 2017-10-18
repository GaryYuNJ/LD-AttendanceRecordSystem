package ldars;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestResouce {
//	private static Logger logger = Logger.getLogger(CustomerAPITest.class);
//	@Resource(name="nodeService")
//	private INodeService iNodeService;
//	@Resource(name="iResourceService")
//	private IResourceService iResourceService;
//	@Test
//	public void testAddNode(){
//		NodeModel nodeModel=new NodeModel();
//		nodeModel.setName("绿地大楼12");
//		nodeModel.setParentId(1);
//		logger.info(iNodeService.addNode(nodeModel));
//		logger.info(nodeModel.getId());
//	}
//	
//	@Test
//	public void updateAddNode(){
//		NodeModel nodeModel=new NodeModel();
//		nodeModel.setName("绿地大楼121");
//		nodeModel.setId(15);
//		nodeModel.setParentId(1);
//		logger.info(iNodeService.updateNode(nodeModel));
//		logger.info(nodeModel.getId());
//	}
//
//	@Test
//	public void deleteNode(){
//		logger.info(iNodeService.deleteNode(15));
//	}
//	@Test
//	public void testGetAllNode(){
//		logger.info(JSON.toJSON(iNodeService.getAllNodeTree()));
//	}
//	@Test
//	public void testCreateResource(){
//		ResourceModel rm=new ResourceModel();
//		rm.setName("闸机01");
//		rm.setTypeId(1);
//		//rm.setMac("xxxx");
//		rm.setNodeId(1);
//		//rm.setCreateUser(1000);
//		iResourceService.createResource(rm);
//	}
//	//测试资源组新增
//	@Test
//	public void testCreateResouceGr(){
//		
//	}
//	//测试获取数量
//	@Test
//	public void testgetCount(){
//		ResourceModel rm=new ResourceModel();
//		rm.setName("闸机");
//		rm.setBuildingId(0);
//		//logger.info(iResourceService.queryCountByCondition(rm));
//	}
//	//条件查询
//	@Test
//	public void testQueryCondition(){
//		ResourceModel rm=new ResourceModel();
//		rm.setName("闸机");
//		//logger.info(JSON.toJSON(iResourceService.queryBasicResByCondition(rm, 1, 10)));
//	}
//	
//	@Test
//	public void testJsontoObject(){
//		ResourceModel rm=new ResourceModel();
//		rm.setName("闸机");
//		/*ResourceKey rk=new ResourceKey();
//		rk.setPassword("1123");
//		List<ResourceKey> rks=new ArrayList<ResourceKey>();
//		rks.add(rk);
//		rm.setResourceKeys(rks);
//		String v=JSON.toJSON(rm).toString();
//		ResourceModel r2=JSON.parseObject(v, ResourceModel.class);
//		logger.info("--");*/
//	}
}
