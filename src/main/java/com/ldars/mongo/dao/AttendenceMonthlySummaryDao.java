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

import com.ldars.mongo.bo.AttendenceMonthlySummaryBo;

@Repository
public class AttendenceMonthlySummaryDao {
	
//		/**
//		 * 操作mongodb的类,可以参考api
//		 */
//		@Autowired
//		private MongoTemplate mongoTemplate;
//		
//		/**
//		 * 获取所有集合的名称
//		 * @return
//		 * @author huweijun
//		 * @date 2016年7月7日 下午8:27:28
//		 */
//		public Set<String> getCollectionNames() {
//			Set<String> collections = mongoTemplate.getCollectionNames();
//			return collections;
//		}
//		
//		public AttendenceMonthlySummaryBo saveOrUpdate(AttendenceMonthlySummaryBo attenMonthlySummaryBo){
//			mongoTemplate.save(attenMonthlySummaryBo);
//			//attenMonthlySummaryBo = findById(attenMonthlySummaryBo.getId());
//			return attenMonthlySummaryBo;
//		}
//		
//		public AttendenceMonthlySummaryBo findById(String id){
//			return mongoTemplate.findById(id, AttendenceMonthlySummaryBo.class);
//		}
//		
//		public AttendenceMonthlySummaryBo findByMonth(String Month){
//			Query query = new Query(Criteria.where("month").is(Month));
//			return mongoTemplate.findOne(query, AttendenceMonthlySummaryBo.class);
//		}
}
