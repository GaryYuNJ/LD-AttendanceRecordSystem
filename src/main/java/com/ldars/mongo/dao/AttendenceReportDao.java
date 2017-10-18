package com.ldars.mongo.dao;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ldars.data.BootstrapTableData;
import com.ldars.mongo.bo.AttendenceReportBo;
import com.ldars.mongo.bo.Pager;
import com.mysql.jdbc.StringUtils;

@Repository
public class AttendenceReportDao {

	private static final Logger logger = LoggerFactory.getLogger(AttendenceReportDao.class);
	
		/**
		 * 操作mongodb的类,可以参考api
		 */
		@Autowired
		private MongoTemplate mongoTemplate;
		
		/**
		 * 保存用户信息
		 * @param attendenceReportBo
		 * @return
		 * @author huweijun
		 * @date 2016年7月7日 下午8:27:37
		 */
		public AttendenceReportBo save(AttendenceReportBo attendenceReportBo) {
			mongoTemplate.save(attendenceReportBo);
			return attendenceReportBo;
		}

		/**
		 * 获取所有集合的名称
		 * @return
		 * @author huweijun
		 * @date 2016年7月7日 下午8:27:28
		 */
		public Set<String> getCollectionNames() {
			Set<String> collections = mongoTemplate.getCollectionNames();
			return collections;
		}
		
		/**
		 * 分页查询数据
		 * @param attendenceReportBo
		 * @param pager
		 * @return
		 * @author Gary
		 * @date 2017年10月18日 下午8:27:47
		 */
		public List<AttendenceReportBo> selectPagebyConditions(String company, String month, String realName, String mobile, BootstrapTableData pager){
			Query query = new Query();
			query.skip((pager.getPage()-1)*pager.getPageSize());
			query.limit(pager.getPageSize());
			Order order = new Order(Direction.ASC, "company");

			Criteria criteria = new Criteria();
			if(!StringUtils.isNullOrEmpty(company)){
				criteria.andOperator(Criteria.where("company").is(company));
			}
			if(!StringUtils.isNullOrEmpty(month)){
				criteria.andOperator(Criteria.where("month").is(month));
			}
			if(!StringUtils.isNullOrEmpty(realName)){
				criteria.andOperator(Criteria.where("realName").is(realName));
			}
			if(!StringUtils.isNullOrEmpty(mobile)){
				criteria.andOperator(Criteria.where("mobile").is(mobile));
			}
			
			query.addCriteria(criteria);
			
			query.with(new Sort(order));
			//query.addCriteria(new Criteria("userNo").in("NO1468048113823"));
			
			Long total = mongoTemplate.count(query, AttendenceReportBo.class);
			List<AttendenceReportBo> arbos = mongoTemplate.find(query, AttendenceReportBo.class);
			
			//pager.setRows(arbos);
			pager.setTotal(total);
			return arbos;
		}
		
//		public AttendenceReportBo findById(String id){
//			Query query = new Query();
//			query.addCriteria(new Criteria("_id").is(id));
//			AttendenceReportBo attendenceReportBo = mongoTemplate.findOne(query, AttendenceReportBo.class);
//			return attendenceReportBo;
//		}
		
		public AttendenceReportBo saveOrUpdate(AttendenceReportBo attendenceReportBo){
			mongoTemplate.save(attendenceReportBo);
			//attendenceReportBo = findById(attendenceReportBo.getId());
			return attendenceReportBo;
		}
		public AttendenceReportBo findById(String id){
			return mongoTemplate.findById(id, AttendenceReportBo.class);
		}
		
		public AttendenceReportBo findByMonthAndMobile(String month,String mobile){
			
			Criteria criatira = new Criteria();
			criatira.andOperator(Criteria.where("mobile").is(mobile), 
					Criteria.where("month").is(month));
			
			return mongoTemplate.findOne(new Query(criatira), AttendenceReportBo.class);
		}
		
		
		public List<AttendenceReportBo> list(){
			Query query = new Query();
			//query.addCriteria(new Criteria("_id").is(id));
			//Criteria criteria = new Criteria("name").is("洪磊");
			Criteria criteria = Criteria.where("name").is("洪磊");
			query.limit(10);
			query.skip(1);
			//Criteria criteria = new Criteria("tags").elemMatch(new Criteria("name").is("tagName_10"));
			query.addCriteria(criteria);
			//query.addCriteria(new Criteria("title").is("关于中国"));
			List<AttendenceReportBo> attendenceReportBoList = mongoTemplate.find(query, AttendenceReportBo.class);
			return attendenceReportBoList;
		}
}
