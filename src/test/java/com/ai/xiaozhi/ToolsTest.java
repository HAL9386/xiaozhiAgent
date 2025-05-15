package com.ai.xiaozhi;

import com.ai.xiaozhi.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToolsTest {
  @Autowired
  private SeparateChatAssistant separateChatAssistant;

  @Test
  public void testCalculatorTools() {
    String answer = separateChatAssistant.chat(5, "1加2等于多少？475695037565的平方根是多少？");
    System.out.println(answer);
  }
}
