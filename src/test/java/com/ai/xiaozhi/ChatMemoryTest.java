package com.ai.xiaozhi;

import com.ai.xiaozhi.assistant.Assistant;
import com.ai.xiaozhi.assistant.MemoryChatAssistant;
import com.ai.xiaozhi.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChatMemoryTest {
  @Autowired
  private QwenChatModel qwenChatModel;
  @Test
  public void testChatMemory() {
    MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
    Assistant assistant = AiServices
      .builder(Assistant.class)
      .chatModel(qwenChatModel)
      .chatMemory(chatMemory)
      .build();
    String answer1 = assistant.chat("解释一下java中的反射机制");
    System.out.println(answer1);
    String answer2 = assistant.chat("给出其经典的应用场景");
    System.out.println(answer2);
  }

  @Autowired
  private MemoryChatAssistant memoryChatAssistant;
  @Test
  public void testMemoryChatAssistant() {
    String answer1 = memoryChatAssistant.chat("我是ElePhantom");
    System.out.println(answer1);
    String answer2 = memoryChatAssistant.chat("我是谁");
    System.out.println(answer2);
  }

  @Autowired
  private SeparateChatAssistant separateChatAssistant;
  @Test
  public void testSeparateChatAssistant() {
    String answer1 = separateChatAssistant.chat(1, "我是ElePhantom");
    System.out.println(answer1);
    String answer2 = separateChatAssistant.chat(1, "我是谁");
    System.out.println(answer2);
    String answer3 = separateChatAssistant.chat(2, "我是谁");
    System.out.println(answer3);
  }
}
