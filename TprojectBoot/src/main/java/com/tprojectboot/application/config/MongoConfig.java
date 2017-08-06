package com.tprojectboot.application.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Configuration
public class MongoConfig {
	@Bean
	public MongoTemplate mongoTemplate() throws IOException {
		MongoTemplate mongoTemplate = null;
		
		System.out.println("**********MongoDB Setting***********");
		
		//몽고디비 설정//
		MongodStarter starter = MongodStarter.getDefaultInstance();
		
		String bindIp = "127.0.0.1";
		int port = 27017;
		
		IMongodConfig mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(bindIp, port, Network.localhostIsIPv6()))
				.build();
		
		MongodExecutable mongodExecutable = null;
		try {
			mongodExecutable = starter.prepare(mongodConfig);
			MongodProcess mongod = mongodExecutable.start();

			MongoClient mongo = new MongoClient(bindIp, port);
			mongoTemplate = new MongoTemplate(mongo, "local");
			
			DB db = mongo.getDB("local");
			DBCollection col = db.createCollection("memberdb", new BasicDBObject()); //컬렉션을 생성//
			
			//mongodb data setting
			for(int i=0; i<3; i++){
				col.save(new BasicDBObject("username", "admin"+(i+1)).append("password", "admin"+(i+1)).append("role", "ROLE_ADMIN"));
			}
			
		} finally {
			if (mongodExecutable != null){

			}
			
			else{
				
			}
		}
		
		System.out.println("************************************");
		
		return mongoTemplate;
	}
}
