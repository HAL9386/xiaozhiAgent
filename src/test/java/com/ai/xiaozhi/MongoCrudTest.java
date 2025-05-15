package com.ai.xiaozhi;

import com.ai.xiaozhi.bean.ChatMessages;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MongoCrudTest {
  @Autowired
  private MongoTemplate mongoTemplate;
//  @Test
//  public void testInsert() {
//    ChatMessages chatMessages = new ChatMessages(1L, "Hello, World!");
//    mongoTemplate.insert(chatMessages);
//  }

  @Test
  public void testInsertWithObjectId() {
    ChatMessages chatMessages = new ChatMessages();
    chatMessages.setContent("聊天记录");
    mongoTemplate.insert(chatMessages);
  }

  @Test
  public void testFindById() {
    String id = "68255caaa7dca35800b67b62";
    ChatMessages chatMessages = mongoTemplate.findById(id, ChatMessages.class);
    System.out.println(chatMessages);
  }

  @Test
  public void testUpdate() {
    String id = "68255caaa7dca35800b67b62";
    Criteria criteria = Criteria.where("_id").is(id);
    Query query = new Query(criteria);
    Update update = new Update().set("content", "更新后的聊天记录");
    mongoTemplate.upsert(query, update, ChatMessages.class);
  }

  @Test
  public void testDelete() {
    String id = "68255caaa7dca35800b67b62";
    Criteria criteria = Criteria.where("_id").is(id);
    Query query = new Query(criteria);
    mongoTemplate.remove(query, ChatMessages.class);
  }
}
