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

import com.ldars.mongo.bo.AttendenceBo;
import com.ldars.mongo.bo.Pager;

/**
 * 用户DAO
 * @author huweijun
 * @date 2016年7月7日 下午8:49:18
 */
@Repository
public class AttendenceDao {

	private static final Logger logger = LoggerFactory.getLogger(AttendenceDao.class);
	
		/**
		 * 操作mongodb的类,可以参考api
		 */
		@Autowired
		private MongoTemplate mongoTemplate;
		
		/**
		 * 保存用户信息
		 * @param attendenceBo
		 * @return
		 * @author huweijun
		 * @date 2016年7月7日 下午8:27:37
		 */
		public AttendenceBo save(AttendenceBo attendenceBo) {
			mongoTemplate.save(attendenceBo);
			return attendenceBo;
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
		 * @param attendenceBo
		 * @param pager
		 * @return
		 * @author huweijun
		 * @date 2016年7月7日 下午8:27:47
		 */
		public Pager selectPage(AttendenceBo attendenceBo,Pager pager){
			Query query = new Query();
			query.skip((pager.getPageNum()-1)*pager.getPageSize());
			query.limit(pager.getPageSize());
			Order order = new Order(Direction.DESC, "id");

			query.with(new Sort(order));
			//query.addCriteria(new Criteria("userNo").in("NO1468048113823"));
			long total = mongoTemplate.count(query, AttendenceBo.class);
			List<AttendenceBo> users = mongoTemplate.find(query, AttendenceBo.class);
			pager.setResult(users);
			pager.setTotal(total);
			return pager;
		}
		
//		public AttendenceBo findById(String id){
//			Query query = new Query();
//			query.addCriteria(new Criteria("_id").is(id));
//			AttendenceBo attendenceBo = mongoTemplate.findOne(query, AttendenceBo.class);
//			return attendenceBo;
//		}
		
		public AttendenceBo saveOrUpdate(AttendenceBo attendenceBo){
			mongoTemplate.save(attendenceBo);
			//attendenceBo = findById(attendenceBo.getId());
			return attendenceBo;
		}
		public AttendenceBo findById(String id){
			return mongoTemplate.findById(id, AttendenceBo.class);
		}
		
		public List<AttendenceBo> list(){
			Query query = new Query();
			//query.addCriteria(new Criteria("_id").is(id));
			//Criteria criteria = new Criteria("name").is("洪磊");
			Criteria criteria = Criteria.where("name").is("洪磊");
			query.limit(10);
			query.skip(1);
			//Criteria criteria = new Criteria("tags").elemMatch(new Criteria("name").is("tagName_10"));
			query.addCriteria(criteria);
			//query.addCriteria(new Criteria("title").is("关于中国"));
			List<AttendenceBo> attendenceBoList = mongoTemplate.find(query, AttendenceBo.class);
			return attendenceBoList;
		}
		
		public List<AttendenceBo> findByStartEndDate(Long startTime, Long endTime){
			return mongoTemplate.find(new Query(Criteria.where("attendence_time").gte(startTime).lte(endTime)),
					AttendenceBo.class);
		}
		
		public  List<AttendenceBo> findByStartEndDateAndMobile(Long startTime,Long endTime, String mobile){
			Criteria criteria = new Criteria();
			criteria.andOperator(Criteria.where("mobile").is(mobile), 
					Criteria.where("attendence_time").gte(startTime).lte(endTime));
			
			Query query = new Query();
			Order order = new Order(Direction.ASC, "attendence_time");
			query.with(new Sort(order));
			//query.limit(10);
			//query.skip(1);
			//Criteria criteria = new Criteria("tags").elemMatch(new Criteria("name").is("tagName_10"));
			query.addCriteria(criteria);
			
			return mongoTemplate.find(query, AttendenceBo.class);
		}
		
		public AttendenceBo findByAttendanceDateMobileAndTag(String attDate, String mobile, String tag, int sort){
			Criteria criatira = new Criteria();
			criatira.andOperator(Criteria.where("mobile").is(mobile), 
					Criteria.where("attendence_date").is(attDate), 
					Criteria.where("tag").is(tag));
			
			Query query = new Query();
			
			Order order;
			if(sort == 0){
				order = new Order(Direction.ASC, "attendence_time");
			}else{
				order = new Order(Direction.DESC, "attendence_time");
			}
			query.with(new Sort(order));
			query.addCriteria(criatira);
			
			return mongoTemplate.findOne(query, AttendenceBo.class);
		}
		
		public List<AttendenceBo> findByAttendanceDateAndMobile(String attDate, String mobile){
			Criteria criatira = new Criteria();
			criatira.andOperator(Criteria.where("mobile").is(mobile), 
					Criteria.where("attendence_date").is(attDate));
			
			Query query = new Query();
			Order order = new Order(Direction.ASC, "attendence_time");
			
			query.with(new Sort(order));
			query.addCriteria(criatira);
			
			return mongoTemplate.find(query, AttendenceBo.class);
		}
}
