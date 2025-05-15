package com.ai.xiaozhi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "chat_messages")
public class ChatMessages {
  @Id
  private ObjectId messageId; // 唯一标识，映射到文档的_id字段

  private String content; // 存储当前聊天记录列表的json字符串
}
